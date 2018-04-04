package com.naskar.router.servlet;

import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Route {
	<S, R> Mapping<S, R> to(Function<HttpServletRequest, S> in, BiConsumer<R, HttpServletResponse> out);
}
