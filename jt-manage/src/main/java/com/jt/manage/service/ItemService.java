package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

public interface ItemService {
	EasyUIResult findItemByPage(Integer page,Integer rows);
	
	String findItemCatById(Long itemId);

	void updateItem(Item item,String desc);

	void updateStatus(Long[] ids, int status);

	void deleteItem(Long[] ids);

	void saveItem(Item item, String desc);

	ItemDesc findItemDescById(Long itemId);

	Item findItemById(Long itemId);


	 
	
	
	
	}
