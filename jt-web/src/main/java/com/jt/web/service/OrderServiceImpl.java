package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Order;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;


@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private HttpClientService httpClient;
	@Autowired
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String saveOrder(Order order) {
		String orderId = null;
		String url = "http://order.jt.com/order/create";
		try {
			String orderJSON = objectMapper.writeValueAsString(order);
			Map<String,String> params = new HashMap<>();
			params.put("orderJSON", orderJSON);
			String resultJSON = httpClient.doPost(url,params);
			
			SysResult sysResult = objectMapper.readValue(resultJSON, SysResult.class);
			if(sysResult.getStatus() == 200){
				 orderId = (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderId;
	}

	@Override
	public Order findOrderById(String id) {
		Order order = null;
		String url = "http://order.jt.com/order/query/"+id;
		String orderJSON = httpClient.doGet(url);
		try {
			order = objectMapper.readValue(orderJSON, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
	
}
