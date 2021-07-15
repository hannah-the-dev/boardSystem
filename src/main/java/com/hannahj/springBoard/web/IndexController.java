package com.hannahj.springBoard.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hannahj.springBoard.config.auth.dto.SessionUser;
import com.hannahj.springBoard.domain.Category;
import com.hannahj.springBoard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    final HttpSession httpSession;

    @GetMapping({ "/index", "/" })
    @ResponseBody
    public List<Category> categoryPage(Model model) {
        model.addAttribute("title", "Home");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        System.out.println(httpSession.toString());
        if(user != null) {
            model.addAttribute("user", user);
        }

        List<Category> categoryList= categoryRepo.findAll();
        model.addAttribute(categoryList);
        
        return categoryList;
    }

}
