package com.lin.config;

import com.lin.component.LoginHandlerInterceptor;
import com.lin.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer来扩展SpringMvc的功能（配置类）
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/lin").setViewName("success");
        //registry.addRedirectViewController("/lin1","success");
        //访问localhost：8080自动跳转login.html页面
        registry.addViewController("/").setViewName("login");
        //访问login.html自动跳转login.html
        registry.addViewController("/login.html").setViewName("login");

        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/login.html","/","/user/login","/asserts/**","/webjars/**","/resources/**");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
