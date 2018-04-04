package com.naskar.router.servlet.impl;

import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naskar.router.servlet.Mapping;
import com.naskar.router.servlet.Route;

public class RouteImpl implements Route {
	
	private Function<HttpServletRequest, ?> in;
	private BiConsumer<?, HttpServletResponse> out;
	private MappingImpl<?, ?> mapping;
	
	@SuppressWarnings("unchecked")
	@Override
	public <S, R> Mapping<S, R> to(Function<HttpServletRequest, S> in, BiConsumer<R, HttpServletResponse> out) {
		this.in = in;
		this.out = out;
		this.mapping = new MappingImpl<S, R>();
		return (Mapping<S, R>) mapping;
	}
	
	public MappingImpl<?, ?> getMapping() {
		return mapping;
	}
	
	public Function<HttpServletRequest, ?> getIn() {
		return in;
	}
	
	@SuppressWarnings("unchecked")
	public BiConsumer<Object, HttpServletResponse> getOut() {
		return (BiConsumer<Object, HttpServletResponse>) out;
	}
	
}