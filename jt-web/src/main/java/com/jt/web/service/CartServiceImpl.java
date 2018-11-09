package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Cart;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private HttpClientService httpClient;
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<Cart> findCartByUserId(Long userId) {
		//1.定义远程url
		String url = "http://cart.jt.com/cart/query/"+userId;
		
		//2.http提交(GET)
		String resultJSON = httpClient.doGet(url);
		List<Cart> cartList = null;
		try {
			SysResult sysResult = objectMapper.readValue(resultJSON, SysResult.class);
			cartList = (List<Cart>) sysResult.getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return cartList;
	}

	@Override
	public void saveCart(Cart cart) {
		//1.定义远程url
		String url = "http://cart.jt.com/cart/save/";
		
		Map<String,String> params = new HashMap<>();
		params.put("userId",cart.getUserId()+"");
		params.put("itemId",cart.getItemId()+"");
		params.put("itemTitle", cart.getItemTitle());
		params.put("itemImage", cart.getItemImage());
		params.put("itemPrice", cart.getItemPrice()+"");
		params.put("num", cart.getNum()+"");
		
		//post提交
		httpClient.doPost(url,params);
	}

	@Override
	public void updateCart(Cart cart) {
		//1.定义远程url
		String url = "http://cart.jt.com/cart/update/num/";
		//封装参数
		//Map<String,String> params = new HashMap<>();
		try {
			String cartJSON = objectMapper.writeValueAsString(cart);
			Map<String,String> params = new HashMap<>();
			params.put("cartJSON", cartJSON);
			httpClient.doGet(url, params);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void deleteCartByUI(Cart cart) {
		String url = "http://cart.jt.com/cart/delete/";
		try {
			String cartJSON = objectMapper.writeValueAsString(cart);
			Map<String,String> params = new HashMap<>();
			params.put("cartJSON", cartJSON);
			httpClient.doGet(url, params);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
}
