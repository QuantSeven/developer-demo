package com.pousheng.demo.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pousheng.demo.service.user.UserService;
import com.pousheng.demo.web.controller.base.AbstractController;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成
 * 
 * @author
 */
@Controller
public class LoginController extends AbstractController {

	private UserService userService;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "loginForm", method = { RequestMethod.POST, RequestMethod.GET })
	public String loginForm() throws Exception {
		return "login";
	}

	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public String getIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().setAttribute("username", username);
		return "redirect:index";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public String logout(HttpSession session, HttpServletRequest request) throws Exception {
		session.setMaxInactiveInterval(0);
		return "redirect:loginForm";
	}
}
