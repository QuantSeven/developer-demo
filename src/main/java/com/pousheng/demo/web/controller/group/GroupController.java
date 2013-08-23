package com.pousheng.demo.web.controller.group;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.pousheng.demo.model.group.Group;
import com.pousheng.demo.service.group.GroupService;
import com.pousheng.demo.web.controller.base.AbstractController;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.Json;
import com.pousheng.demo.web.ui.PageRequest;

import framework.generic.utils.string.StringUtil;

@Controller
@RequestMapping("group/*")
public class GroupController extends AbstractController<Group, String> {

	private GroupService groupService;

	@Resource
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("group/group_list");
	}

	@Override
	public DataGrid dataGrid(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "filter_");
		pageRequest.setParameter(paramMap);
		return groupService.getDatagrid(pageRequest);
	}

	@Override
	public ModelAndView addFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("action", "group/insert");
		return new ModelAndView("group/group_edit");
	}

	@Override
	public Json insert(Group group, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			groupService.create(group);
			return success(getMessage("msg.success.add"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.add"));
		}
	}

	@Override
	public ModelAndView editForm(String groupId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("group", groupService.get(groupId));
		request.setAttribute("action", "group/update");
		return new ModelAndView("group/group_edit");
	}

	@Override
	public Json update(Group group, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			groupService.modify(group);
			return success(getMessage("msg.success.update"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.update"));
		}
	}

	@Override
	public Json delete(String groupId, HttpServletRequest request, HttpServletResponse response) {
		try {
			groupService.remove(groupId);
			return success(getMessage("msg.success.delete"));
		} catch (Exception e) {
			e.printStackTrace();
			return error(getMessage("msg.error.delete"));
		}
	}

	@Override
	public ModelAndView view(String groupId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("group", groupService.get(groupId));
		return new ModelAndView("group/group_edit");
	}

	@Override
	public boolean validatePk(String groupId, HttpServletRequest request, HttpServletResponse response) {
		Group group = groupService.get(groupId);
		if (StringUtil.isNullOrEmpty(group)) {
			return true;
		}
		return false;
	}

}
