package com.jt.cart.mapper;

import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Cart;

public interface CartMapper extends SysMapper<Cart>{
	
	@Select("select * from tb_cart where user_id=#{userId} and item_id=#{itemId}")
	Cart findCartByUI(Cart cart);

	void updateCartNum(Cart cart);

}
