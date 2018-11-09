package com.jt.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestObjectMapper {
	//
	@Test
	public void ObjectToJSON() throws JsonProcessingException{
		User user = new User();
		user.setId(1);
		user.setName("Tom");
		user.setAge(18);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		System.out.println(json);
		
	}
	
	@Test
	public void JsonToObject() throws JsonParseException, JsonMappingException, IOException{
		String json = "{\"id\":\"1\",\"name\":\"tom\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(json,User.class);
		System.out.println(user);
	}
	
	@Test
	public void jsonOA() throws Exception{
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setId(1);
		user1.setName("tom");
		user1.setAge(18);
		new User();
		User user2 = new User();
		user2.setId(2);
		user2.setName("jerry");
		user2.setAge(20);
		userList.add(user1);
		userList.add(user2);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(userList);
		System.out.println("获取的JSON串为:"+json);
		
		User[] users = objectMapper.readValue(json, User[].class);
		List<User> uList = Arrays.asList(users);
		System.out.println("获取的List集合为:"+uList);
	}
}
