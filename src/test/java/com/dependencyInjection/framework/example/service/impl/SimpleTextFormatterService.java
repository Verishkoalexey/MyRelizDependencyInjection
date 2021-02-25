package com.dependencyInjection.framework.example.service.impl;


import com.dependencyInjection.framework.example.service.TextFormatterService;

public class SimpleTextFormatterService implements TextFormatterService {

	@Override
	public String format(String text) {
		return "Simple text: " + text;
	}

}
