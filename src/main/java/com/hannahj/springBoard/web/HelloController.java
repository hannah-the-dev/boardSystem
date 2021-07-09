package com.hannahj.springBoard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello")
	public String helloSpringBoot(Model model) {
		model.addAttribute("name", "안설란");
		return "hello";
	}
}
