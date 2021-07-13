package com.hannahj.springBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
@EntityScan("com.hannahj.springBoard.domain")
@EnableJpaAuditing
@EnableJpaRepositories("com.hannahj.springBoard.repository")
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
