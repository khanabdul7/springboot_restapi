package com.initiallyrics.rest.webservices.restful.web_services.rest_example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private MessageSource messageSource; // for fetching msgs define in messages.properties

	public RestController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@GetMapping("/welcome")
	public String welcomeMessage() {

		return "Good Morning";
	}

	@GetMapping("/welcome_internationalized")
	public String welcomeMessageInternationalized() {
		Locale locale = LocaleContextHolder.getLocale(); // fetching locale from req. ex: en for English

		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
