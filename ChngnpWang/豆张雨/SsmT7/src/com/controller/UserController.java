package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.T00_user;

@Controller
public class UserController {
	@RequestMapping("/login")
	public String login(T00_user t00_user,Model model) {
		model.addAttribute("t00_user",t00_user);
		return "login";
	}
}
