package com.naskar.router.servlet.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naskar.router.servlet.Route;
import com.naskar.router.servlet.Router;

public class RouterImpl implements Router {
	
	private Map<String, RouteImpl> routes;
	
	public RouterImpl() {
		this.routes = new HashMap<>();
	}
	
	@Override
	public Route from(String path) {
		RouteImpl r = new RouteImpl();
		routes.put(path, r);
		return r;
	}

	public void execute(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getPathInfo();
		RouteImpl route = routes.get(path); 
		
		if(route == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Object s = route.getIn().apply(req);
		
		Object of = route.getMapping().getCall().apply(req, res, s);
		
		route.getOut().accept(of, res);
	}
}