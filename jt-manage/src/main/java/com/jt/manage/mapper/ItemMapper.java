package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{
	
	
	List<Item> findItemByPage(
			@Param("startIndex")Integer startIndex,
			@Param("rows")Integer rows);
	
	//@Select("select count(*) from tb_item")
	//int findItemCount();
	
	@Select("select name from tb_item_cat where id=#{itemId}")
	String findItemCatById(Long itemId);

	void updateStatus(@Param("ids")Long[] ids,@Param("status")int status);

	void updateByPrimaryKeySelective(Item item, String desc);
}
