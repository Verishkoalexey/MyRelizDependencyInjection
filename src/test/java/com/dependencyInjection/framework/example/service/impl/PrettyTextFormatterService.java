package com.dependencyInjection.framework.example.service.impl;


import com.dependencyInjection.framework.example.service.TextFormatterService;

public class PrettyTextFormatterService implements TextFormatterService {

	@Override
	public String format(String text) {
		return "Pretty text: <" + text + ">";
	}

}
