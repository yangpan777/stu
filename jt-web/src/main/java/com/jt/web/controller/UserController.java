package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/{module}")
	public String register(@PathVariable String module){
		return module;
	}
	
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user){
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户添加失败");
	}
	
	/**
	 * 实现用户的登录操作
	 * @param user
	 * @return
	 * 核心：将用户token的秘钥写入浏览器cookie中
	 * cookie.setMaxAge(3600 * 24 * 7);	//七天超时
	 * cookie.setMaxAge(0);    			//表示立即删除
	 * cookie.setMaxAge(-1);   			//表示会话关闭后删除
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult findUserByUp(User user,HttpServletResponse response){
		try {
			String token = userService.findUserByUp(user);
			if(StringUtils.isEmpty(token)){
				return SysResult.build(201, "用户名或密码错误");
			}
			//如果程序执行到这，表示token不为空，将数据写入cookie中
			Cookie cookie = new Cookie("JT_TICKET",token);
			cookie.setMaxAge(3600 * 24 * 7);//七天超时
			cookie.setPath("/");				//表示cookie的所有者
			response.addCookie(cookie);			//将cookie写入浏览器中
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户名或密码错误");	
	}
	/**
	 * 1.通过JT_TICKET 或者token数据
	 * 2.根据token删除redis缓存数据
	 * 3.将cookie删除
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//1.获取JT_TICKET的值
		Cookie[] cookies = request.getCookies();
		String token = null;
		for(Cookie cookie : cookies){
			if("JT_TICKET".equals(cookie.getName())){
				token = cookie.getValue();
				break;
			}
		}
		if(!StringUtils.isEmpty(token)){
			//表示token数据不为空，先删缓存
			jedisCluster.del(token);
			
			//删除cookie
			Cookie cookie = new Cookie("JT_TICKET","");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		//重定向到商城主页
		return "redirect:/index.html";
	}
	
	
}
