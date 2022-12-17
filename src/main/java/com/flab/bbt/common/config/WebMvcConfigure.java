//package com.flab.bbt.common.config;
//
//import java.util.List;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfigure implements WebMvcConfigurer {
//    @Bean
//    public PageableHandlerMethodResolver pageableHandlerMethodResolver() {
//        return new PageableHandlerMethodResolver();
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(pageableHandlerMethodResolver());
//    }
//}
