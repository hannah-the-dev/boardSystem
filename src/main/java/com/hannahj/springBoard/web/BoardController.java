package com.hannahj.springBoard.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.BoardItem;
import com.hannahj.springBoard.paging.Criteria;
import com.hannahj.springBoard.repository.BoardItemRepository;
import com.hannahj.springBoard.repository.BoardItemSpecs;
import com.hannahj.springBoard.repository.BoardRepository;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private BoardItemRepository postRepo;

//	@GetMapping(value="/")
//	public String list(Pageable pageable, Model model) {	    
//	    model.addAttribute("boardList", boardRepo.findAll(pageable));
//	    return "/index";
//	}

    @GetMapping({ "/index", "/" })
    public String boardList(@PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable,
            Model model) {

        Page<Board> boardPage = boardRepo.findAll(pageable);
        Criteria criteria = new Criteria(boardPage);
        model.addAttribute("startBlockPage", criteria.getStartBlockPage());
        model.addAttribute("endBlockPage", criteria.getEndBlockPage());
        model.addAttribute("boardPage", boardPage);

        return "/index";
    }

    @GetMapping({ "/board" })
    public String postList(@RequestParam(value = "id", defaultValue = "1") Long id,
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable, Model model) {
        Optional<Board> boardOptional = boardRepo.findById(id);
        Board board = Board.builder().build();
        if (!boardOptional.isPresent()) {
            return null;
        } else {
            board = boardOptional.get();
        }
        Page<BoardItem> postPage = postRepo.findAllByBoardAndParentIdIsNull(board, pageable);
        Criteria criteria = new Criteria(postPage);
        model.addAttribute("startBlockPage", criteria.getStartBlockPage());
        model.addAttribute("endBlockPage", criteria.getEndBlockPage());
        model.addAttribute("postPage", postPage);
        model.addAttribute("board", board);
        return "/board";
    }

    @GetMapping("/write")
    @Transactional
    public String write(@RequestParam(value = "board", defaultValue = "1") Long id, Model model) {
        List<Board> boards = boardRepo.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("id", id);
        return "/write";
    }

//	@GetMapping("/search")
//	@ResponseBody @Transactional(readOnly = true)
//	 public List<BoardItem> search(
//	         @RequestParam(value="keywords") String keywords,
//	         @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable
//	         ) {
//	    BoardItemSpecs postSpecs = new BoardItemSpecs();
////	    String expression = postSpecs.getExpression(keywords);
//	    List<BoardItem> found = postRepo.searching(keywords);
////	    List<BoardItem> list = found.getContent();
//        return found;
//	    
//	}

    @GetMapping(value = "/search")
    @ResponseBody
    public Optional<Page<BoardItem>> search(
            @RequestParam(value = "keywords") String keywords,
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable, 
            Model model) {
        Map<String, Object> filter = new HashMap<String, Object>();
        String[] searches = keywords.split(" ");
        for(String search: searches) {
            filter.put("title", "%" + search + "%");
            filter.put("content", "%" + search + "%");
        }

        Page<BoardItem> page = postRepo.findAllByParentIdIsNullAndTitleLike(keywords, pageable);
        return Optional.of(page);
    }
	
	

}
