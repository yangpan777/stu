package com.jt.manage.controller.web;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

@RequestMapping("/web")
@Controller
public class JSONPController {
//		@RequestMapping(value="/testJSONP",produces="text/html;charset=utf-8")
//		@ResponseBody
//		public String testJSONP(String callback) throws JsonProcessingException{
//			User user = new User();
//			user.setId(100);
//			user.setName("峰会上对方is");
//			
//			
//			
//			ObjectMapper objectMapper = new ObjectMapper();
//			String result = objectMapper.writeValueAsString(user);
//			return callback+"("+result+")";
//		}
		
	//利用工具类自动实现JSONP数据返回
		@RequestMapping(value="/testJSONP")
		@ResponseBody
		public MappingJacksonValue testJSONP(String callback) throws JsonProcessingException{
			User user = new User();
			user.setId(100);
			user.setName("非常及时答复");
			
			
			MappingJacksonValue value = new MappingJacksonValue(user);
			value.setJsonpFunction(callback);
			return value;
			

}
}
