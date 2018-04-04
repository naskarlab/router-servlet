package com.naskar.router.servlet.impl;

import java.util.function.Function;

import com.naskar.router.servlet.Handler;
import com.naskar.router.servlet.Mapping;

public class MappingImpl<S, R> implements Mapping<S, R> {
	
	private Handler<S, R> call;
	
	@Override
	public void execute(Handler<S, R> call) {
		this.call = call;
	}
	
	@Override
	public void execute(Function<S, R> call) {
		execute((req, res, r) -> call.apply(r));
	}
	
	@SuppressWarnings("unchecked")
	public Handler<Object, Object> getCall() {
		return (Handler<Object, Object>) call;
	}
}