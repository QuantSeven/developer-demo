package com.pousheng.demo.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pousheng.demo.model.user.User;

import framework.generic.mybatis.dao.GenericDao;
public interface UserDao extends GenericDao<User, String> {

	/**
	 * 根据主键查询一条记录
	 */
	User findByPk(@Param("userId") String userId);

	int deleteByPk(@Param("userId") String userId);

	User update(User user);

	List<User> findByGroupId(@Param("groupId") String groupId);
}
