package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> filterRegistrationBeanRegion() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<JwtFilter>();
        bean.setFilter(jwtFilter);

        bean.addUrlPatterns("/profile/*");
        bean.addUrlPatterns("/article/adm/*");
        bean.addUrlPatterns("/type/*");
        bean.addUrlPatterns("/category/adm/*");
        bean.addUrlPatterns("/comment/user/*");
        bean.addUrlPatterns("/region/adm/*");
        bean.addUrlPatterns("/article_like/*");
        bean.addUrlPatterns("/comment_like/*");
        bean.addUrlPatterns("/save_articles/*");
        bean.addUrlPatterns("/profile/public/*");
        bean.addUrlPatterns("/profile/public/*");
        return bean;
    }
}
