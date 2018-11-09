package com.jt.manage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.ItemParam;

public interface ItemParamService {
	EasyUIResult findPageObjects(Integer pageCurrent,Integer rows);
}
