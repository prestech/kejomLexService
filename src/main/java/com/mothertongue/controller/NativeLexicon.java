package com.mothertongue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/native")
public class NativeLexicon {

	@GetMapping("/synonym")
	public String getSynonym(){
		
		return "hello";
	}
}
