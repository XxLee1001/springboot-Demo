package com.lxx.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxx.model.User;
import com.lxx.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	// 登录页面跳转
	@RequestMapping("/login")
	public String login(ModelMap map) {
		map.addAttribute("user", new User());
		return "login";
	}

	// 登录逻辑处理
	@RequestMapping("/userLogin")
	public String userLogin(User user, Map<String, Object> map, HttpSession session) {
		// 判断用户名和密码是否正确
		if (userService.check(user.getUserName(), user.getPassword()).isEmpty()) {
			// 不正确返回错误信息
			map.put("msg", "用户名或密码错误");
			return "/login";
		} else {
			// 校验正确后存入session
			session.setAttribute("loginUser", user.getUserName());
			return "redirect:/list";
		}
	}

	// @RequestMapping("/notVerify")
	// @ResponseBody
	// String notVerify() {
	// return "用户名或密码错误";
	// }

	// @RequestMapping("/userLogin")
	// public ModelAndView userLogin(User user) {
	// if (userService.check(user.getUserName(), user.getPassword()).isEmpty()) {
	// ModelAndView modelAndView = new ModelAndView();
	//
	// return modelAndView.addObject("用户名或密码错误") ;
	// } else {
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.setViewName("redirect:/list");
	// return modelAndView;
	// }
	// }

}
