package com.hannahj.springBoard.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hannahj.springBoard.config.auth.dto.SessionUser;
import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.Post;
import com.hannahj.springBoard.paging.Criteria;
import com.hannahj.springBoard.repository.BoardRepository;
import com.hannahj.springBoard.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private PostRepository postRepo;
    //Since http session has only constructor, @RequiredArgsConstructor construct automatically
    
    private HttpSession httpSession;
    
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
        Page<Post> postPage = postRepo.findAllByBoardAndParentIdIsNull(board, pageable);
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
    public String write(
            @RequestParam(value = "board", defaultValue = "1") Long id, 
            HttpSession httpSession, 
            Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user);
        }
        List<Board> boards = boardRepo.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("id", id);
        model.addAttribute("title", "글쓰기");
        return "/write";
    }
}
