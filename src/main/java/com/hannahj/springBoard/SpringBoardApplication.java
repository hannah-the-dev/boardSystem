package com.hannahj.springBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoardApplication.class, args);
	}
	
	@Bean 
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
	    return p -> { 
	        p.setOneIndexedParameters(true); // 페이지 1부터 시작 
	    };
	}

}
