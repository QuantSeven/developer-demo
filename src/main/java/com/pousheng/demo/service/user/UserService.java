package com.pousheng.demo.service.user;

import java.util.List;

import com.pousheng.demo.model.user.User;
import com.pousheng.demo.web.ui.DataGrid;

import framework.generic.mybatis.page.Pagination;

public interface UserService {

	DataGrid getPage(Pagination pagination);

	User insert(User user);

	User update(User user);

	int deleteByPk(String userId);

	User findByPK(String userId);
	
	List<User> findAll();

}
