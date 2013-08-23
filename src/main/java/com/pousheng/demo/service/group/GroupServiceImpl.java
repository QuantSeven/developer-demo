package com.pousheng.demo.service.group;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pousheng.demo.dao.group.GroupDao;
import com.pousheng.demo.model.group.Group;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.PageRequest;

import framework.generic.mybatis.paginator.domain.Order;
import framework.generic.mybatis.paginator.domain.PageBounds;
import framework.generic.mybatis.paginator.domain.PageList;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;

	@Resource
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public DataGrid getDatagrid(PageRequest pageRequest) {
		PageBounds pageBounds = new PageBounds(pageRequest.getPageNo(), pageRequest.getPageSize(),Order.formString(pageRequest.getSortString()));
		List<Group> groups = groupDao.findByPage(pageRequest.getParameter(), pageBounds);
		PageList<Group> pageList = (PageList<Group>) groups;
		return new DataGrid(pageList.getPaginator().getTotalCount(), groups);
	}

	@Override
	public Group create(Group group) {
		groupDao.insertEntity(group);
		return group;
	}

	@Override
	public Group modify(Group group) {
		groupDao.updateEntity(group);
		return group;
	}

	@Override
	public int remove(String groupId) {
		return groupDao.deleteByPk(groupId);
	}

	@Override
	public Group get(String groupId) {
		return groupDao.findByPk(groupId);
	}

	@Override
	public List<Group> read() {
		return groupDao.findAll();
	}

}
