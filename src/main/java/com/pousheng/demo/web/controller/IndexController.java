package com.pousheng.demo.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pousheng.demo.web.controller.base.AbstractController;

@Controller
public class IndexController extends AbstractController {

	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public String getIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "index";
	}
}
