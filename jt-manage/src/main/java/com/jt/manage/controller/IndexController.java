package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	/**
	 * 
	 * 多个URL请求动态获取，只用一个方法
	 * 要求：
	 * 	1.参数位置必须固定
	 *  2.如果有多个参数时使用/进行分割
	 *  3.需要接受的参数使用{}包装，使用@PathVariable注解接收参数
	 *  4.如果参数接受名称不一致，可以使用
	 *  @PathVariable(value=aaa),
	 *  以上为restFul
	 * @return
	 */
	@RequestMapping("/page/{moduleName}")
	public String item(@PathVariable String moduleName){
		return moduleName;
	}
}
