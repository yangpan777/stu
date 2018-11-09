package com.jt.sys.dao;

import java.util.List;

import com.jt.sys.entity.SysLog;

/**
 * DAO:数据访问对象
 * 定义访问数据库的相关方法
 */
public interface SysLogDao {
	List<SysLog> findPageObjects();
}








