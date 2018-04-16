package com.lxx.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxx.model.User;
import com.lxx.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping("/successpage")
	String successpage() {
		return "successpage";

	}

	// 用户注册跳转
	@RequestMapping("/register")
	public String register(ModelMap map) {
		map.addAttribute("user", new User());
		return "register";
	}

	// 用户注册逻辑处理
	@RequestMapping("/userRegister")
	public String registerUser(User user, Map<String, Object> map) {
		// 判断用户名是否已被注册
		if (userService.register(user.getUserName()) == null) {
			// 注册成功后返回成功页面
			userService.save(user);
			return "redirect:/successpage";
		} else {
			// 失败提示错误信息
			map.put("msg", "该用户名已被注册");
			return "/register";
		}

	}

	// @RequestMapping("/errorpage")
	// @ResponseBody
	// String errorpage() {
	// return "该用户名已被注册";
	// }

}
