package com.initiallyrics.rest.webservices.restful.web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

	@GetMapping("/filtering")  //field 2 will filter in response, bcos we used @JsonIgnore in POJO class
	public SomeBean someBean() {
		return new SomeBean("Value1","Value2","Value3");
	}
	
	@GetMapping("/filtering_list") 
	public List<SomeBean> someBeanList() {
		return Arrays.asList(new SomeBean("Value1","Value2","Value3"),
				new SomeBean("Value4","Value5","Value6")) ;
	}
}
