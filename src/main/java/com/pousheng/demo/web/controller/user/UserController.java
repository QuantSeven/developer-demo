package com.pousheng.demo.web.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import framework.generic.mybatis.page.Pagination;
import framework.generic.utils.string.StringUtil;

@Controller
@RequestMapping("user/*")
public class UserController extends AbstractController {

	private UserService userService;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;

	}

	/*-------------------------------列表显示页面---------------------------------*/
	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request) {
		return "user/user_list";
	}
	

	@RequestMapping(value = "listData", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public DataGrid listData(Pagination pagination, HttpServletRequest request) {
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "sch_");
		pagination.setParameter(paramMap);
		DataGrid dg = userService.getPage(pagination);
		return dg;
	}
	@RequestMapping(value = "userList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<User> userList(HttpServletRequest request) {
		return userService.findAll();
	}

	/*--------------------------------添加操作-----------------------------------*/
	@RequestMapping(value = "addForm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(Model model, HttpServletRequest request) {
		model.addAttribute("action", "user/insert");
		return new ModelAndView("user/user_edit");
	}

	@RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Json insert(@ModelAttribute("user") User user) {
		try {
			if (!StringUtil.isNullOrEmpty(user)) {
				user = userService.insert(user);
				//LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(), getIp(), "ADD_USER", getEmployeeName() + "(" + getEmployeeCode() + ")" + "添加了一条ID_为：(" + user.getId() + ")的数据记录:" + user);
			}
			return success("user/index", getMessage("msg.success.add"));
		} catch (Exception e) {
			//ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(), getEmployeeName() + "(" + getEmployeeCode() + ")" + "添加User为：(" + user + ")的时候报错.");
			e.printStackTrace();
			return error("user/index", getMessage("msg.error.add"));
		}
	}

	/*--------------------------------编辑操作-----------------------------------*/
	@RequestMapping(value = "editForm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(@RequestParam("userId") String userId, Model model, HttpServletRequest request) {
		User user = userService.findByPK(userId);
		model.addAttribute("user", user);
		model.addAttribute("action", "user/update");
		return new ModelAndView("user/user_edit");
	}

	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Json update(HttpServletRequest request, @ModelAttribute("user") User user) {
		try {
			if (StringUtil.isNullOrEmpty(user.getUserId())) {
				return error("user/index", getMessage("msg.error.update"));
			}
			userService.update(user);
			// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(), getIp(), "UPDATE_USER", getEmployeeName() + "(" + getEmployeeCode() + ")" + "修改了一条ID_为：(" + user.getId() + ")的数据:" + user);
			return success("user/index", getMessage("msg.success.update"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(), getEmployeeName() + "(" + getEmployeeCode() + ")" + "更新ID_为：(" + user.getId() + ")的时候报错.");
			e.printStackTrace();
			return error("user/index", getMessage("msg.error.update"));
		}
	}

	/*--------------------------------删除操作-----------------------------------*/
	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Json delete(@RequestParam("userId") String id) {
		try {
			int result = userService.deleteByPk(id);
			if (result <= 0) {
				return error("user/index", getMessage("user.msg.error.userassociated"));
			}
			// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(), getIp(), "DELETE_USER", getEmployeeName() + "(" + getEmployeeCode() + ")" + "删除用户ID_为(" + id + ")的数据记录。");
			return success(getMessage("msg.success.delete"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(), getEmployeeName() + "(" + getEmployeeCode() + ")" + "用户ID_为：(" + id + ")的数据时报错.");
			e.printStackTrace();
			return error("user/index", getMessage("msg.error.delete"));
		}
	}

	/*--------------------------------查看-----------------------------------*/
	@RequestMapping(value = "view", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView view(Model model, @RequestParam("userId") String userId) {
		model.addAttribute("user", userService.findByPK(userId));
		model.addAttribute("hideBtnSave", "false");
		return new ModelAndView("user/user_edit");
	}

	@RequestMapping(value = "validateId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public boolean validateId(Model model, @RequestParam("userId") String userId) {
		User user = userService.findByPK(userId);
		if (StringUtil.isNullOrEmpty(user)) {
			return true;
		}
		return false;
	}
}
