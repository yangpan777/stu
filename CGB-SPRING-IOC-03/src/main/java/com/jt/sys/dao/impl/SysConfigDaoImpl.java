package com.jt.sys.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
/**
 * 思考:如何将此对象交给Spring管理
 */
public class SysConfigDaoImpl implements SysLogDao {
    private SqlSessionFactory sqlSessionFactory;
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	/**
     * 思考:如何在如下方法中通过mybatis对象
     * 访问数据库获取日志信息
     */
	public List<SysLog> findPageObjects() {
		//1.获取SqlSession对象
		SqlSession session=
		sqlSessionFactory.openSession();
		//2.执行查询操作
		String statement=
		"com.jt.sys.dao.SysLogDao.findPageObjects";
		List<SysLog> list=
		session.selectList(statement);
		//3.释放资源
		session.close();
		//4.返回结果
		return list;
	}

}
