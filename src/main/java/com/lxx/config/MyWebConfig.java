package com.lxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {

		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

			@Override
			public void addInterceptors(InterceptorRegistry registry) {

				// 设置要拦截的地址以及放行的地址
				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(
						"/login", "/register", "/successpage", "/userAdd", "/userEdit", "/userFind", "/userLogin",
						"/logout", "/userRegister", "/toAdd", "/toEdit");
			}

		};
		return adapter;
	}

}
