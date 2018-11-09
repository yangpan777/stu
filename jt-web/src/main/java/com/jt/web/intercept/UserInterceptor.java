package com.jt.web.intercept;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.parser.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.web.thread.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

public class UserInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JedisCluster jedisCluster;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	/**处理器执行之前，校验用户是否登录
	 * 1.通过request对象先获取cookie
	 * 2.获取token数据
	 * 3.从redis集群中获取userJSON数据
	 * 4.将userJSON串转化为user对象
	 * 5.从对象中动态获取id
	 * 6.如果上述业务中token/userJSON为null，都要进行拦截
	 * 跳转到从系统的登录页面。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = null;
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if("JT_TICKET".equals(cookie.getName())){	
				token = cookie.getValue();
				break;
			}
		}
		
		if(!StringUtils.isEmpty(token)){
			String userJSON = jedisCluster.get(token);
			if(!StringUtils.isEmpty(userJSON)){
				User user = objectMapper.readValue(userJSON, User.class);
				//动态获取Id
				//request.getSession();
				
				//利用userThreadLocal动态获取id
				UserThreadLocal.set(user);
				//用户已登录，true表示放行
				return true;
			}
		}
		//重定向到登录页面
		response.sendRedirect("/user/login.html");
		return false;
	}

	//处理器执行完之后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {		
	}
	//用户展现数据之前
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {	
		UserThreadLocal.remove();
	}

}
