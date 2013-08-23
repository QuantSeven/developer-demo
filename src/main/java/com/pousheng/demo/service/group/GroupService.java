package com.pousheng.demo.service.group;

import java.util.List;

import com.pousheng.demo.model.group.Group;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.PageRequest;

public interface GroupService {

	DataGrid getDatagrid(PageRequest pageRequest);

	Group create(Group group);

	Group modify(Group group);

	int remove(String groupId);

	Group get(String groupId);

	List<Group> read();

}
