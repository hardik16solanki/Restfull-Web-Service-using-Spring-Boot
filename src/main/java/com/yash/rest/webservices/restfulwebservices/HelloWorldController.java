package com.yash.rest.webservices.restfulwebservices;



import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

//	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld(){
		return "Hello World";
	}
	
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello World");
	}
	
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
		return new HelloWorldBean(String.format("Hello World, %s",name));
	}
	
//	@GetMapping("/hello-world-internationalization")
//	public String helloWorldInternationalization(@RequestHeader(name="Accept-Language",required=false) Locale locale){
//		return messageSource.getMessage("good.morning.message", null, locale);
//	}
	
	@GetMapping("/hello-world-internationalization")
	public String helloWorldInternationalization(){
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
