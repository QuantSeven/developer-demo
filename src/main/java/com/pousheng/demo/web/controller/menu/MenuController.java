package com.pousheng.demo.web.controller.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pousheng.demo.model.menu.Menu;
import com.pousheng.demo.service.menu.MenuService;
import com.pousheng.demo.web.controller.base.AbstractController;

@Controller
@RequestMapping("menu/*")
public class MenuController extends AbstractController {

	private MenuService menuService;

	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value = "/listData", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Menu> listData() {
		List<Menu> menus = menuService.findAll();
		System.out.println(menus.size());
		return menus;
	}

}
