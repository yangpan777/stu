package com.jt.web.service;

import java.util.List;

import com.jt.common.po.Cart;

public interface CartService {

	List<Cart> findCartByUserId(Long userId);

	void saveCart(Cart cart);

	void updateCart(Cart cart);

	void deleteCartByUI(Cart cart);

}
