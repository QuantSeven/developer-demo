package com.pousheng.demo.dao.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.pousheng.demo.model.user.User;

import framework.generic.mybatis.db.DbSqlSessionDaoSupport;
import framework.generic.mybatis.page.Pagination;

@Repository("userDao")
public class UserDaoImpl extends DbSqlSessionDaoSupport implements UserDao {

	@Override
	public int deleteByPK(String userId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userId", userId);
		return getSqlSession().delete("deleteByPk", paramMap);
	}

	@Override
	public User findByPK(String userId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userId", userId);
		return getSqlSession().selectOne("selectByPk", paramMap);
	}

	@Override
	public List<User> findByPage(Pagination pagination) {
		return getSqlSession().selectPage("selectByPage", pagination);
	}

	@Override
	public Integer count() {
		return (Integer) getSqlSession().selectOne("selectCount");
	}

	@Override
	public User insert(User user) {
		return getSqlSession().insertObject(user);
	}

	@Override
	public User update(User user) {
		return getSqlSession().updateObject(user);
	}

	@Override
	public int deleteByPk(String userId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userId", userId);
		return getSqlSession().delete("deleteByPk", paramMap);
	}

}
