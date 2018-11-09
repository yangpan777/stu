package com.jt.web.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private HttpClientService httpClientService;
	
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	//发送http请求
	@Override
	public Item findItemById(Long itemId) {
		String url = "http://manage.jt.com/web/item/"+itemId;
		
		//ItemJSON串
		String result = httpClientService.doGet(url);
		//System.out.println(result);
		Item item =null;
		try {
			item = objectMapper.readValue(result, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("item:"+item);
		return item;
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.jt.com/web/item/desc/"+itemId;
		String result = httpClientService.doGet(url);
		ItemDesc itemDesc = null;
		try {
			itemDesc = objectMapper.readValue(result,ItemDesc.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("item:"+itemDesc);
		return itemDesc;
	}


}
