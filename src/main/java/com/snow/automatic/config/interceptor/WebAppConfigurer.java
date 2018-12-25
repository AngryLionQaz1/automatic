package com.snow.automatic.config.interceptor;



import com.snow.automatic.common.bean.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {


    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private Config config;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        InterceptorRegistration in=registry.addInterceptor(authorizationInterceptor);
        setPath(config,in);
    }


    /**获取路径*/
    public static String[] path(String str){
        return str.split(",");
    }


    /**设置路径*/
    public static void setPath(Config properties, InterceptorRegistration http) {
        String[] addPath=path(properties.getAddPath());
        String[] excludePath=path(properties.getExcludePath());
        Arrays.stream(addPath).forEach(i->http.addPathPatterns(i));
        Arrays.stream(excludePath).forEach(i->http.excludePathPatterns(i));
    }






}
