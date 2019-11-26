package com.example.safe.config;

import com.example.safe.interceptor.URLInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Author: tbw
 * @Date: 2019/11/22 16:57
 */
@Configuration
public class MyWebAppConfig implements WebMvcConfigurer {


        @Bean
        public HandlerInterceptor getMyInterceptor(){
            return new URLInterceptor();
        }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
    }
}
