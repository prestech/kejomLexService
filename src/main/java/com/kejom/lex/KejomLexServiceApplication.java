package com.kejom.lex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kejom.lex.util.DataSource;

//@SpringBootApplication
public class KejomLexServiceApplication {

	public static void main(String[] args) throws JsonProcessingException {
		//SpringApplication.run(KejomLexServiceApplication.class, args);
		//System.out.println("Hello World"); 
		System.out.println(DataSource.pkgLexToJsonListObj()); 
	}

}
//missing tools.jar