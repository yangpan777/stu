package com.jt.manage.service;

import java.util.List;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.vo.EasyUITree;

public interface ItemCatService {
	List<EasyUITree> findItemCatById(Long parentId);

	List<EasyUITree> findCacheItemCatById(Long parentId);
}
