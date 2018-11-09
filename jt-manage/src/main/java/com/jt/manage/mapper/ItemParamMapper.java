package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.ItemParam;

public interface ItemParamMapper extends SysMapper<ItemParam>{
	List<ItemParam> findPageObjects(
						@Param("startIndex")Integer startIndex,
						@Param("pageSize")Integer rows);
	
	@Select("select count(*) from tb_item_param")
	int selectCount();
}
