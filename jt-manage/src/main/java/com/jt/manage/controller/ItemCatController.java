package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
//	@RequestMapping("/add")
//	@ResponseBody
//	public String add(){
//		return "add";
//	}
	//实现商品分类目录的展现
	//
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCatById(@RequestParam(value="id",defaultValue="0") Long parentId){
		//查询一级商品分类
		//Long parentId = id;
		//return itemCatService.findItemCatById(parentId);
		return itemCatService.findCacheItemCatById(parentId);
	}
}
