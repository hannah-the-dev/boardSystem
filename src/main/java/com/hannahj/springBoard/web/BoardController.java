package com.hannahj.springBoard.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hannahj.springBoard.config.auth.dto.SessionUser;
import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.BoardItem;
import com.hannahj.springBoard.paging.Criteria;
import com.hannahj.springBoard.repository.BoardItemRepository;
import com.hannahj.springBoard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private BoardItemRepository postRepo;
    //Since http session has only constructor, @RequiredArgsConstructor construct automatically
    final HttpSession httpSession;

    @GetMapping({ "/index", "/" })
    public String boardList(@PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable,
            Model model) {

        Page<Board> boardPage = boardRepo.findAll(pageable);
        Criteria criteria = new Criteria(boardPage);
        model.addAttribute("startBlockPage", criteria.getStartBlockPage());
        model.addAttribute("endBlockPage", criteria.getEndBlockPage());
        model.addAttribute("boardPage", boardPage);
        model.addAttribute("title", "Main");

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute(user);
        }
        
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
        model.addAttribute("page", postPage);
        model.addAttribute("board", board);
        model.addAttribute("title", board.getTitle());
        return "/board";
    }

    @GetMapping("/write")
    @Transactional
    public String write(@RequestParam(value = "board", defaultValue = "1") Long id, Model model) {
        List<Board> boards = boardRepo.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("id", id);
        model.addAttribute("title", "글쓰기");
        return "/write";
    }

	@GetMapping({"/search", "/post/search"})
	 public String search(
	         @RequestParam(value="keywords") String keywords,
	         @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable,
	         Model model) {
//	    BoardItemSpecs postSpecs = new BoardItemSpecs();
//	    String expression = BoardItemSpecs.getExpression(keywords);
        System.out.println(pageable.toString());
//	        Page<BoardItem> found = postRepo.findAllPostsWithTitleAndContentLike(expression, pageable);
        String[] words = keywords.split(" ");
        Map<Long, BoardItem> result = new LinkedHashMap<>();
        for(String word : words) {
            List<BoardItem> posts = postRepo.findByParentIdIsNullAndTitleLikeIgnoreCaseOrParentIdIsNullAndContentLikeIgnoreCase('%'+word+'%','%'+word+'%');
            for (BoardItem post: posts) {
                result.put(post.getId(), post);
            }
        }
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > result.size() ? result.size() :
            (start + pageable.getPageSize());
        Page<BoardItem> pagedResult = new PageImpl<> ((new ArrayList<BoardItem> (result.values())).subList(start,end), pageable, pageable.getPageSize());
	    model.addAttribute("page", pagedResult);
	    model.addAttribute("keyword",keywords);
	    
	    Criteria criteria = new Criteria(pagedResult);
        model.addAttribute("startBlockPage", criteria.getStartBlockPage());
        model.addAttribute("endBlockPage", criteria.getEndBlockPage());
        model.addAttribute("title", "검색 결과");	    
        return "/search";	    
	}
	
	@GetMapping("/join") 
    public String join(Model model) {
	    
	    SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute(user);
        }
        return "join";
	    
	}
}
