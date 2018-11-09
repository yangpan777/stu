package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private HttpClientService httpClient;
	@Autowired
	private static ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void saveUser(User user) {
		//定义远程url
		String url = "http://sso.jt.com/user/register";
		
		Map<String,String> params = new HashMap<>();
		params.put("username",user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		
		String sysResultJSON = httpClient.doPost(url, params);
		//判断成功与否
		try {
			SysResult sysResult = 
					objectMapper.readValue(sysResultJSON, SysResult.class);
			if(sysResult.getStatus() != 200){
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	/**
	 * 1.定义远程url
	 * 2.封装参数 Map
	 * 3.发起请求之后，校验用户返回值是否正确，获取token数据
	 * 4.将正确的数据返回
	 */
	@Override
	public String findUserByUp(User user) {
		String token = null;
		//定义远程url
		String url = "http://sso.jt.com/user/login";
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUsername());
		//根据需求实现，应该加密后传输
		String md5Pass = DigestUtils.md5Hex(user.getPassword());
		params.put("password", md5Pass);
		
		//发起http请求
		String sysResultJSON = httpClient.doPost(url,params);
		
		try {
			SysResult sysResult = 
					objectMapper.readValue(sysResultJSON, SysResult.class);
			if(sysResult.getStatus() == 200){
				token = (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}

}
