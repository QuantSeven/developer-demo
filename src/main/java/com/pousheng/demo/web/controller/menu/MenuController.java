package com.pousheng.demo.web.controller.menu;

import java.sql.Timestamp;
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

import com.pousheng.demo.model.menu.Menu;
import com.pousheng.demo.service.menu.MenuService;
import com.pousheng.demo.web.controller.base.AbstractController;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.Json;

import framework.generic.mybatis.page.Pagination;
import framework.generic.utils.string.StringUtil;

@Controller
@RequestMapping("menu/*")
public class MenuController extends AbstractController {

	private MenuService menuService;

	@Resource
	public void setMenmenuvice( MenuService menuService) {
		this.menuService = menuService;
	}

    /**
     * 转到 菜单管理主界面
     * @return
     */
    @RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
    public String index() {
        return "menu/menu_list";
    }

	@RequestMapping(value = "/menuList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Menu> list() {
		List<Menu> menus = menuService.findAll();
		return menus;
	}

    @RequestMapping(value = "listData", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public DataGrid listData(Pagination pagination, HttpServletRequest request) {
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "sch_");
        pagination.setParameter(paramMap);
        DataGrid dg = menuService.getPage(pagination);
        return dg;
    }

    /*--------------------------------添加操作-----------------------------------*/
    @RequestMapping(value = "addForm", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView add(Model model, HttpServletRequest request) {
        model.addAttribute("action", "menu/insert");
        return new ModelAndView("menu/menu_edit");
    }

    @RequestMapping(value = "insert", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Json insert(@ModelAttribute("menu") Menu menu,HttpServletRequest request) {
        try {
            if (!StringUtil.isNullOrEmpty(menu)) {
                if(!StringUtil.isNullOrEmpty(request.getSession().getAttribute("username")))
                menu.setCreateUser(request.getSession().getAttribute("username").toString());
                menu.setCreateDate(new Timestamp(System.currentTimeMillis()));
                menu = menuService.insert(menu);
                // LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(), getIp(), "ADD_menu", getEmployeeName() + "(" + getEmployeeCode() + ")" + "添加了一条ID_为：(" + menu.getId() + ")的数据记录:" + menu);
            }
            return success("menu/index", getMessage("msg.success.add"));
        } catch (Exception e) {
            // ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(), getEmployeeName() + "(" + getEmployeeCode() + ")" + "添加menu为：(" + menu + ")的时候报错.");
            e.printStackTrace();
            return error("menu/index", getMessage("msg.error.add"));
        }
    }

    /*--------------------------------编辑操作-----------------------------------*/
    @RequestMapping(value = "editForm", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView edit(@RequestParam("menuId") String menuId, Model model, HttpServletRequest request) {
        Menu menu = menuService.findByPK(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("action", "menu/update");
        return new ModelAndView("menu/menu_edit");
    }

    @RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Json update(HttpServletRequest request, @ModelAttribute("menu") Menu menu) {
        try {
            if (StringUtil.isNullOrEmpty(menu.getMenuId())) {
                return error("menu/index", getMessage("msg.error.update"));
            }


            menuService.update(menu);
            // LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(), getIp(), "UPDATE_menu", getEmployeeName() + "(" + getEmployeeCode() + ")" + "修改了一条ID_为：(" + menu.getId() + ")的数据:" + menu);
            return success("menu/index", getMessage("msg.success.update"));
        } catch (Exception e) {
            // ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(), getEmployeeName() + "(" + getEmployeeCode() + ")" + "更新ID_为：(" + menu.getId() + ")的时候报错.");
            e.printStackTrace();
            return error("menu/index", getMessage("msg.error.update"));
        }
    }

    /*--------------------------------删除操作-----------------------------------*/
    @RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Json delete(@RequestParam("menuId") String id) {
        try {
            int result = menuService.deleteByPk(id);
            if (result <= 0) {
                return error("menu/index", getMessage("menu.msg.error.menuassociated"));
            }
            // LoggingUtil.info(CommonConstant.APP_ID, getEmployeeCode(), getIp(), "DELETE_menu", getEmployeeName() + "(" + getEmployeeCode() + ")" + "删除用户ID_为(" + id + ")的数据记录。");
            return success(getMessage("msg.success.delete"));
        } catch (Exception e) {
            // ExceptionUtil.error(CommonConstant.APP_ID, e, this.getClass(), getEmployeeName() + "(" + getEmployeeCode() + ")" + "用户ID_为：(" + id + ")的数据时报错.");
            e.printStackTrace();
            return error("menu/index", getMessage("msg.error.delete"));
        }
    }

    /*--------------------------------查看-----------------------------------*/
    @RequestMapping(value = "view", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView view(Model model, @RequestParam("menuId") String menuId) {

        model.addAttribute("menu", menuService.findByPK(menuId));
        model.addAttribute("action","menu/index");
        model.addAttribute("hideBtnSave", "false");
        return new ModelAndView("menu/edit");
    }

    @RequestMapping(value = "validateId", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public boolean validateId(@RequestParam("menuId") String menuId) {
        Menu menu = menuService.findByPK(menuId);
        if (StringUtil.isNullOrEmpty(menu)) {
            return true;
        }
        return false;
    }


}
