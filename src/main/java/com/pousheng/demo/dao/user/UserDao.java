package com.pousheng.demo.dao.user;

import java.util.List;

import com.pousheng.demo.model.user.User;

import framework.generic.mybatis.page.Pagination;

public interface UserDao {

	/**
	 * 根据主键删除一条记录
	 */
	int deleteByPK(String userId);

	/**
	 * 根据主键查询一条记录
	 */
	User findByPK(String userId);

	List<User> findByPage(Pagination pagination);

	Integer count();

	User insert(User user);

	User update(User user);

	int deleteByPk(String userId);

}
