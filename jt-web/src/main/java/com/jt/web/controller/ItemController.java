package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.web.service.ItemService;
@Controller
@RequestMapping("/items/")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("{itemId}")
	public String findItemById(@PathVariable Long itemId, Model model){
		Item item = itemService.findItemById(itemId);
		model.addAttribute("item", item);
		
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		model.addAttribute("itemDesc", itemDesc);
		 //跳转至商品展现页面
		return "item";
	}
}
