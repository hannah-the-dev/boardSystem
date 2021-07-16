package com.hannahj.springBoard.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hannahj.springBoard.domain.Category;
import com.hannahj.springBoard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    final HttpSession httpSession;
    
    @GetMapping("/")
    public String adminDashboard(
            @PageableDefault(sort = { "id" }, direction = Direction.DESC) Pageable pageable, 
            Model model) {
        model.addAttribute("page", categoryRepo.findAll(pageable));
        return "/admin";
    }
    
    
    @PostMapping("/category/create")
    public String create(
            @ModelAttribute Category category) {
        categoryRepo.save(category);
        return "/category";
    }
    
    @GetMapping("/category/delete")
    public String delete(@ModelAttribute Category category) {
        categoryRepo.save(category);
        return "/category";
    }
}
