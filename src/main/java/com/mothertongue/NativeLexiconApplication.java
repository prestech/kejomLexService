package com.mothertongue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mothertongue.util.DataSource;

@SpringBootApplication
public class NativeLexiconApplication {

	public static void main(String[] args) throws JsonProcessingException {
		
		SpringApplication.run(NativeLexiconApplication.class, args);
		
		//System.out.println("Hello World"); 
		//DataSource.pkgLexToJsonListObj();
		//System.out.println(DataSource.pkgLexToJsonListObj()); 
		
	}

}
//missing tools.jar