package com.naskar.router.servlet.impl;

import java.io.InputStreamReader;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class In {
	
	private static Gson gson = new GsonBuilder().create();

	public static <T> Function<HttpServletRequest, T> fromJson(Class<T> clazz) {
		return (req) -> {
			try {
				return gson.fromJson(new InputStreamReader(req.getInputStream()), clazz);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

}
