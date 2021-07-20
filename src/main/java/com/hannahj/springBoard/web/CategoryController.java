package com.hannahj.springBoard.web;

import java.util.List;
import java.util.Optional;

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

import com.hannahj.springBoard.config.auth.LoginUser;
import com.hannahj.springBoard.config.auth.dto.SessionUser;
import com.hannahj.springBoard.domain.Board;
import com.hannahj.springBoard.domain.Category;
import com.hannahj.springBoard.domain.Post;
import com.hannahj.springBoard.domain.User;
import com.hannahj.springBoard.paging.Criteria;
import com.hannahj.springBoard.repository.BoardRepository;
import com.hannahj.springBoard.repository.CategoryRepository;
import com.hannahj.springBoard.repository.PostRepository;
import com.hannahj.springBoard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;
    //Since http session has only constructor, @RequiredArgsConstructor construct automatically
    
    @GetMapping({ "/category" })
    public String postList(@RequestParam(value = "id", defaultValue = "1") Long id,
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable, 
            @LoginUser SessionUser user,
            Model model) {
//        if the user is login-ed, find that user info and forward to userInfo page
        if(user != null) {
            model.addAttribute("user", user);
        }
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        Category category = Category.builder().build();
        if (!categoryOptional.isPresent()) {
            return null;
        } else {
            category = categoryOptional.get();
        }
        Page<Board> boardPage = boardRepo.findAllByCategory(category, pageable);
        Criteria criteria = new Criteria(boardPage);
        model.addAttribute("startBlockPage", criteria.getStartBlockPage());
        model.addAttribute("endBlockPage", criteria.getEndBlockPage());
        model.addAttribute("page", boardPage);
        model.addAttribute("category", category);
        model.addAttribute("title", category.getTitle());
        return "/category";
    }

}
