package com.naskar.router.servlet.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naskar.router.servlet.Router;

public class RouterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private RouterImpl router;
	
	public RouterServlet() {
		this.router = new RouterImpl();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		configure(config, router);
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.router.execute(req, res);
	}
	
	protected void configure(ServletConfig config, Router router) {
	}

}