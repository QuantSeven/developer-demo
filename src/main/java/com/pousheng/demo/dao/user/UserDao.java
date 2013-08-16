package com.pousheng.demo.dao.user;

import org.apache.ibatis.annotations.Param;

import com.pousheng.demo.model.user.User;

import framework.generic.mybatis.dao.GenericDao;

public interface UserDao extends GenericDao<User, String> {

	/**
	 * 根据主键删除一条记录
	 */
	int deleteByPK(@Param("userId") String userId);

	/**
	 * 根据主键查询一条记录
	 */
	User findByPK(@Param("userId") String userId);

	int deleteByPk(@Param("userId") String userId);

}
