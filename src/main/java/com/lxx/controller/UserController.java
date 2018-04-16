package com.lxx.controller;

import com.lxx.model.User;
import com.lxx.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 首页跳转
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/login";
	}

//	// 查询所有用户，并分页展示
//	@GetMapping(value = "/list")
//	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
//		Pageable pageable = new PageRequest(page, 5);
//		model.addAttribute("page", pageable.getPageNumber());
//		// 判断是否为第一页
//		model.addAttribute("isFirst", pageable.hasPrevious());
//		model.addAttribute("next", pageable.next());
//		model.addAttribute("first", pageable.first());
//		model.addAttribute("pageSize", pageable.getPageSize());
//		Page<User> users = userService.findAll(pageable);
//		model.addAttribute("count", users.getTotalElements());
//		model.addAttribute("pages", users.getTotalPages() - 1);
//		model.addAttribute("users", users);
//		return "user/list";
//	}

	// 添加跳转
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
		return "user/userAdd";
	}

	// 添加逻辑处理
	@RequestMapping(value = "/add")
	public String add(User user, Map<String, Object> map, String userName) {
		// 判断用户名是否存在
		if (userService.findByUserName(userName) == null) {
			userService.save(user);
			return "redirect:/list";
		} else {
			map.put("msg", "该用户名已被占用");
			return "user/userAdd";
		}
	}

	// 编辑跳转
	@RequestMapping(value = "/toEdit")
	public String toEdit(Model model, Long id) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "user/userEdit";
	}

	// 编辑逻辑处理
	@RequestMapping(value = "/edit")
	public String edit(User user, String userName, Map<String, Object> map) {
		// 判断用户名是否被占用
		if (userService.findByUserName(userName) == null) {
			userService.edit(user);
			return "redirect:/list";
		} else {
			map.put("msg", "该用户名已被占用");
			return "user/userEdit";
		}
	}

	// 删除处理
	@RequestMapping(value = "/delete")
	public String delete(Long id) {
		userService.delete(id);
		return "redirect:/list";
	}

	// 查询所有用户合并模糊查询
	@GetMapping(value = "/list")
	public String find(Model model, @RequestParam(value = "page", defaultValue = "0", required=true) Integer page, @RequestParam(value="uString",defaultValue="", required=true) String uString) {
		Pageable pageable = new PageRequest(page, 5);
		model.addAttribute("page", pageable.getPageNumber());
		// 判断是否还有上一页
		model.addAttribute("isFirst", pageable.hasPrevious());
		model.addAttribute("first", pageable.first());
		model.addAttribute("pageSize", pageable.getPageSize());
		Page<User> users = userService.findAllByUserNameLike(uString, pageable);
		model.addAttribute("count", users.getTotalElements());
		// 总页数
		model.addAttribute("pages", users.getTotalPages() - 1);
		model.addAttribute("users", users);
		return "user/list";
	}

	// @PostMapping(value = "/list")
	// public String findBookQuery(Model model,
	// @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
	// @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
	// UserQuery userQuery) {
	// Page<User> users = userService.findByPageAndParams(pageNumber, pageSize,
	// userQuery);
	// model.addAttribute("users", users);
	//
	// return "user/list";
	// }

	// @RequestMapping("/list")
	// public String list(Model model) {
	// List<User> users = userService.getUserList();
	// model.addAttribute("users", users);
	// return "user/list";
	// }

	// @RequestMapping(value = "/add")
	// public String add(User user) {
	// userService.save(user);
	// return "redirect:/list";
	// }

	// @RequestMapping(value = "/edit")
	// public String edit(User user) {
	// userService.edit(user);
	// return "redirect:/list";
	// }

	// @PostMapping(value="/find")
	// public String find(String uString, Model model) {
	// userService.findByUserNameLike(uString);
	//
	// return "/user/list";
	// }

}
