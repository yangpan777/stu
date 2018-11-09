package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemParamMapper;
import com.jt.manage.pojo.ItemParam;

@Service
public class ItemParamServiceImpl implements ItemParamService{
	@Autowired
	private ItemParamMapper itemParamMapper;

	@Override
	public EasyUIResult findPageObjects(Integer pageCurrent, Integer rows) {
		//1.获取商品记录总数
		int total = itemParamMapper.selectCount(null);
		System.out.println(total);
		int startIndex=(pageCurrent-1)*rows;
		System.out.println(rows);
		List<ItemParam> itemParam = itemParamMapper.findPageObjects(startIndex, rows);
		return new EasyUIResult(total,itemParam);
	}
}
