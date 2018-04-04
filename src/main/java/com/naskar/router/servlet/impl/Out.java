package com.naskar.router.servlet.impl;

import java.util.function.BiConsumer;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Out {
	
	private static Gson gson = new GsonBuilder().create();

	public static <R> BiConsumer<R, HttpServletResponse> toJson() {
		return (r, res) -> {
			try {
				res.setContentType("application/json");
				gson.toJson(r, res.getWriter());
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

}