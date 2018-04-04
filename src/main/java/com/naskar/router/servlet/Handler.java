package com.naskar.router.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Handler<S, R> {
	R apply(HttpServletRequest req, HttpServletResponse res, S s);
}
