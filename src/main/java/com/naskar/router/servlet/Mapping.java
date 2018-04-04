package com.naskar.router.servlet;

import java.util.function.Function;

public interface Mapping<S, R> {
	void execute(Function<S, R> call);

	void execute(Handler<S, R> call);
}