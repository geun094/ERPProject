package com.greedy.erp.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	
	@GetMapping("/dddd")
	public void kkkk() {
		System.out.println("ddddd");
	}
}
