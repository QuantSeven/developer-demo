package com.pousheng.demo.web.controller.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.pousheng.demo.service.user.UserService;
import com.pousheng.demo.web.controller.base.AbstractController;
import com.pousheng.demo.web.ui.CommonColumn;
import com.pousheng.demo.web.ui.DataGrid;

import framework.generic.mybatis.page.Pagination;
import framework.generic.utils.json.JsonMapper;
import framework.generic.utils.properties.PropertiesUtil;

@Controller
@RequestMapping("common/*")
public class CommonController extends AbstractController {

	private final JsonMapper mapper = new JsonMapper();
	private UserService userService;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*-------------------------------用户列表显示页面---------------------------------*/
	@RequestMapping(value = "userIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String userIndex(HttpServletRequest request,@RequestParam String columnJson) {
		List<CommonColumn> columns = mapper.fromJson(columnJson, mapper.createCollectionType(List.class, CommonColumn.class));
		request.setAttribute("columns", columns);
		return "common/common_user_list"; 
	}
	
	@RequestMapping(value = "userData", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public DataGrid userData(Pagination pagination, HttpServletRequest request) {
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "sch_");
		map.put("enable", "1");
		pagination.setOrderBy("USER_ID");
		pagination.setParameter(map);
		DataGrid dg = userService.getPage(pagination);
		return dg;
	}
	@RequestMapping(value = "getI18N", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getI18N(@RequestParam(value = "prefix", defaultValue = "") String prefix) {
		return PropertiesUtil.getPropMapStartingWith(prefix, "i18n/messages_" + getLocale() + ".properties");
	}
}