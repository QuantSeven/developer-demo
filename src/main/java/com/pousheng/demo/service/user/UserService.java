package com.pousheng.demo.service.user;

import java.util.List;

import com.pousheng.demo.model.user.User;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.PageRequest;

public interface UserService {

	DataGrid getDatagrid(PageRequest pageRequest);

	User create(User user);

	User modify(User user);

	int remove(String userId);

	User get(String userId);
	
	List<User> read();

	DataGrid getGroupUser(String groupId);

}
