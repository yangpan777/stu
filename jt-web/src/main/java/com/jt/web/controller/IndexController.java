package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//跳转系统首页
	@RequestMapping("/index")
	public String doIndex(){
		return "index";
	}
}
