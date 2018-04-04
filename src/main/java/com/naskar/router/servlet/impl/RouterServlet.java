package com.naskar.router.servlet.impl;

import java.io.IOException;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naskar.router.servlet.Router;

public class RouterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private RouterImpl router;

	public RouterServlet(Consumer<Router> configure) {
		this.router = new RouterImpl();
		configure.accept(this.router);
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.router.execute(req, res);
	}

}