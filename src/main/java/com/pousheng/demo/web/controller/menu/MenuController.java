package com.pousheng.demo.web.controller.menu;

import java.sql.Timestamp;
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

import com.pousheng.demo.model.menu.Menu;
import com.pousheng.demo.service.menu.MenuService;
import com.pousheng.demo.web.controller.base.AbstractController;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.Json;
import com.pousheng.demo.web.ui.PageRequest;

import framework.generic.utils.string.StringUtil;

@Controller
@RequestMapping("menu/*")
public class MenuController extends AbstractController<Menu, String> {

	private MenuService menuService;

	@Resource
	public void setMenmenuvice(MenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("menu/menu_list");
	}
	@Override
	public DataGrid dataGrid(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "filter_");
		pageRequest.setParameter(paramMap);
		DataGrid dg = menuService.getDatagrid(pageRequest);
		return dg;
	}

	@Override
	public ModelAndView addFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("action", "menu/insert");
		request.setAttribute("menus", menuService.read());
		return new ModelAndView("menu/menu_edit");
	}

	@Override
	public Json insert(Menu menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if (!StringUtil.isNullOrEmpty(menu)) {
				if (!StringUtil.isNullOrEmpty(request.getSession().getAttribute("username"))) {
					menu.setCreateUser(request.getSession().getAttribute("username").toString());
				}
				menu.setCreateDate(new Timestamp(System.currentTimeMillis()));
				menu = menuService.create(menu);
				// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(),
				// getIp(), "ADD_menu", getEmployeeName() + "(" +
				// getEmployeeCode() + ")" + "添加了一条ID_为：(" + menu.getId() +
				// ")的数据记录:" + menu);
			}
			return success(getMessage("msg.success.add"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(),
			// getEmployeeName() + "(" + getEmployeeCode() + ")" + "添加menu为：(" +
			// menu + ")的时候报错.");
			e.printStackTrace();
			return error(getMessage("msg.error.add"));
		}
	}

	@Override
	public ModelAndView editForm(String menuId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Menu menu = menuService.get(menuId);
		request.setAttribute("menu", menu);
		request.setAttribute("action", "menu/update");
		request.setAttribute("menus", menuService.read());
		return new ModelAndView("menu/menu_edit");
	}

	@Override
	public Json update(Menu menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if (StringUtil.isNullOrEmpty(menu.getMenuId())) {
				return error( getMessage("msg.error.update"));
			}

			menuService.modify(menu);
			// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(),
			// getIp(), "UPDATE_menu", getEmployeeName() + "(" +
			// getEmployeeCode() + ")" + "修改了一条ID_为：(" + menu.getId() + ")的数据:"
			// + menu);
			return success(getMessage("msg.success.update"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(),
			// getEmployeeName() + "(" + getEmployeeCode() + ")" + "更新ID_为：(" +
			// menu.getId() + ")的时候报错.");
			e.printStackTrace();
			return error(getMessage("msg.error.update"));
		}
	}

	@Override
	public Json delete(String menuId, HttpServletRequest request, HttpServletResponse response) {
		try {
			int result = menuService.remove(menuId);
			if (result <= 0) {
				return error("menu/index", getMessage("msg.error.delete"));
			}
			// LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(),
			// getIp(), "DELETE_menu", getEmployeeName() + "(" +
			// getEmployeeCode() + ")" + "删除用户ID_为(" + id + ")的数据记录。");
			return success(getMessage("msg.success.delete"));
		} catch (Exception e) {
			// ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(),
			// getEmployeeName() + "(" + getEmployeeCode() + ")" + "用户ID_为：(" +
			// id + ")的数据时报错.");
			e.printStackTrace();
			return error("menu/index", getMessage("msg.error.delete"));
		}
	}

	@Override
	public ModelAndView view(String menuId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("menu", menuService.get(menuId));
		request.setAttribute("action", "menu/index");
		return new ModelAndView("menu/menu_edit");
	}

	@Override
	public boolean validatePk(String menuId, HttpServletRequest request, HttpServletResponse response) {
		Menu menu = menuService.get(menuId);
		if (StringUtil.isNullOrEmpty(menu)) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/menuList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Menu> list() {
		List<Menu> menus = menuService.read();
		return menus;
	}
	@RequestMapping(value = "/getParentName", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String getParentName(@RequestParam("menuId") String menuId) {
		Menu menu = menuService.get(menuId);
		return menu.getName();
	}
}
