package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findAll")
	public String findUser(Model model){
		List<User> userList = userService.findUserList();
		//将数据保存到request中
		model.addAttribute("userList", userList);
		//将数据传递到页面中?????
		return "userList";
	}
}
