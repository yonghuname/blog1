package com.eoft.blog2.interceptor;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by limi on 2017/10/15.
 */
@Configuration
public class WebConfig implements  WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
        //排除冲突的路径 ，怕防止拦截一一切后连 admin和登录的login都访问不了
    }
}
