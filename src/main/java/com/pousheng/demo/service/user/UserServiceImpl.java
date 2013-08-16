package com.pousheng.demo.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pousheng.demo.dao.user.UserDao;
import com.pousheng.demo.model.user.User;
import com.pousheng.demo.web.ui.DataGrid;

import framework.generic.mybatis.page.Pagination;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataGrid getPage(Pagination pagination) {
		List<User> results = userDao.findByPage(pagination);
		return new DataGrid(userDao.count(), results);
	}

	@Override
	public User insert(User user) {
		userDao.insertObject(user);
		return user;
	}

	@Override
	public User update(User user) {
		userDao.updateObject(user);
		return user;
	}

	@Override
	public int deleteByPk(String userId) {
		return userDao.deleteByPK(userId);
	}

	@Override
	public User findByPK(String userId) {
		return userDao.findByPK(userId);
	}

	@Override
	public List<User> findAll() {
		return userDao.read();
	}
}
