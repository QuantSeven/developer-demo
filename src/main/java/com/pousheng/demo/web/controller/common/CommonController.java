package com.pousheng.demo.web.controller.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pousheng.demo.web.controller.base.MessageController;
import com.pousheng.demo.web.ui.CommonColumn;

import framework.generic.utils.json.JsonMapper;
import framework.generic.utils.properties.PropertiesUtil;

@Controller
@RequestMapping("common/*")
public class CommonController extends MessageController {

	private final JsonMapper mapper = new JsonMapper();

	/*-------------------------------列表显示页面---------------------------------*/
	@RequestMapping(value = "selectIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public String userIndex(HttpServletRequest request, @RequestParam String columnJson) {
		List<CommonColumn> columns = mapper.fromJson(columnJson, mapper.createCollectionType(List.class, CommonColumn.class));
		request.setAttribute("columns", columns);
		return "common/common_list";
	}

	@RequestMapping(value = "getI18N", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getI18N(@RequestParam(value = "prefix", defaultValue = "") String prefix) {
		return PropertiesUtil.getPropMapStartingWith(prefix, "i18n/messages_" + getLocale() + ".properties");
	}
}