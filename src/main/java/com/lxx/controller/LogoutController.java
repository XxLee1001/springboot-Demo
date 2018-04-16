package com.lxx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	// 注销逻辑处理
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		// 注销后删除session
		session.removeAttribute("loginUser");
		return "login";
	}
}
