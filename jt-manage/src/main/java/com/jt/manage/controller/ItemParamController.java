package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.service.ItemParamService;
import com.jt.manage.vo.EasyUITree;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
		
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult findPageObjects(Integer pageCurrent,Integer rows){
		return itemParamService.findPageObjects(pageCurrent, rows);
	} 
	
}
