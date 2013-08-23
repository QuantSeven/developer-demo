package com.pousheng.demo.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pousheng.demo.dao.user.UserDao;
import com.pousheng.demo.model.group.Group;
import com.pousheng.demo.model.user.User;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.PageRequest;

import framework.generic.mybatis.paginator.domain.Order;
import framework.generic.mybatis.paginator.domain.PageBounds;
import framework.generic.mybatis.paginator.domain.PageList;
import framework.generic.utils.string.StringUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataGrid getDatagrid(PageRequest pageRequest) {
		PageBounds pageBounds = new PageBounds(pageRequest.getPageNo(), pageRequest.getPageSize(),Order.formString(pageRequest.getSortString()));
		List<User> users = userDao.findByPage(pageRequest.getParameter(), pageBounds);
		PageList<User> list = (PageList<User>)users;
		return new DataGrid(list.getPaginator().getTotalCount(), users);
	}

	@Override
	public User create(User user) {
		userDao.insertEntity(user);
		return user;
	}

	@Override
	public User modify(User user) {
		userDao.updateEntity(user);
		return user;
	}

	@Override
	public int remove(String userId) {
		return userDao.deleteByPk(userId);
	}

	@Override
	public User get(String userId) {
		return userDao.findByPk(userId);
	}

	@Override
	public List<User> read() {
		return userDao.findAll();
	}

	@Override
	public DataGrid getGroupUser(String groupId) {
		DataGrid dg;
		if(StringUtil.isNullOrEmpty(groupId)) {
			return new DataGrid();
		}
		return null;
	}
}
