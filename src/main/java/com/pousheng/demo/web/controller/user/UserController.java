package com.pousheng.demo.web.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.pousheng.demo.model.user.User;
import com.pousheng.demo.service.user.UserService;
import com.pousheng.demo.web.controller.base.AbstractController;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.Json;
import com.pousheng.demo.web.ui.PageRequest;

import framework.generic.utils.string.StringUtil;

@Controller
@RequestMapping("user/*")
public class UserController extends AbstractController<User, String> {

	private UserService userService;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;

	}

	/*-------------------------------列表显示页面---------------------------------*/
	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("user/user_list");
	}

	@Override
	public DataGrid dataGrid(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "filter_");
		pageRequest.setParameter(paramMap);
		DataGrid dg = userService.getDatagrid(pageRequest);
		return dg;
	}

	/*--------------------------------添加操作-----------------------------------*/
	@Override
	public ModelAndView addFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("action", "user/insert");
		return new ModelAndView("user/user_edit");
	}

	@Override
	public Json insert(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if (!StringUtil.isNullOrEmpty(user)) {
				user = userService.create(user);
				// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(),
				// getIp(), "ADD_USER", getEmployeeName() + "(" +
				// getEmployeeCode() + ")" + "添加了一条ID_为：(" + user.getId() +
				// ")的数据记录:" + user);
			}
			return success("user/index", getMessage("msg.success.add"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(),
			// getEmployeeName() + "(" + getEmployeeCode() + ")" + "添加User为：(" +
			// user + ")的时候报错.");
			e.printStackTrace();
			return error("user/index", getMessage("msg.error.add"));
		}
	}

	/*--------------------------------编辑操作-----------------------------------*/
	@Override
	public ModelAndView editForm(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = userService.get(userId);
		request.setAttribute("user", user);
		request.setAttribute("action", "user/update");
		return new ModelAndView("user/user_edit");
	}

	@Override
	public Json update(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if (StringUtil.isNullOrEmpty(user.getUserId())) {
				return error("user/index", getMessage("msg.error.update"));
			}
			userService.modify(user);
			// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(),
			// getIp(), "UPDATE_USER", getEmployeeName() + "(" +
			// getEmployeeCode() + ")" + "修改了一条ID_为：(" + user.getId() + ")的数据:"
			// + user);
			return success("user/index", getMessage("msg.success.update"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(),
			// getEmployeeName() + "(" + getEmployeeCode() + ")" + "更新ID_为：(" +
			// user.getId() + ")的时候报错.");
			e.printStackTrace();
			return error("user/index", getMessage("msg.error.update"));
		}
	}
	/*--------------------------------删除操作-----------------------------------*/
	@Override
	public Json delete(String userId, HttpServletRequest request, HttpServletResponse response) {
		try {
			int result = userService.remove(userId);
			if (result <= 0) {
				return error("user/index", getMessage("user.msg.error.userassociated"));
			}
			// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(),
			// getIp(), "DELETE_USER", getEmployeeName() + "(" +
			// getEmployeeCode() + ")" + "删除用户ID_为(" + id + ")的数据记录。");
			return success(getMessage("msg.success.delete"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(),
			// getEmployeeName() + "(" + getEmployeeCode() + ")" + "用户ID_为：(" +
			// id + ")的数据时报错.");
			e.printStackTrace();
			return error("user/index", getMessage("msg.error.delete"));
		}
	}

	/*--------------------------------查看-----------------------------------*/
	@Override
	public ModelAndView view(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("user", userService.get(userId));
		request.setAttribute("hideBtnSave", "false");
		return new ModelAndView("user/user_edit");
	}

	@Override
	public boolean validatePk(String userId, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.get(userId);
		if (StringUtil.isNullOrEmpty(user)) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "userList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<User> userList(HttpServletRequest request) {
		return userService.read();
	}
	@RequestMapping(value = "getGroupUser", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public DataGrid getGroupUser(@RequestParam String groupId,HttpServletRequest request) {
		return userService.getGroupUser(groupId);
	}
}
