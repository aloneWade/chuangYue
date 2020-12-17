package com.cy.asset.common.util.loginUser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author wc
 * @date 2020/12/9
 * @describe
 */

@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 其实从这里看出来，与配置在spring-servlet.xml中相比，都是实例化一个自己注解解析器，
     * 然后将它add进spring的List<HandlerMethodArgumentResolver> argumentResolvers中
     * 最后交给spring统一管理
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(umMethodArgumentResolver());
        argumentResolvers.add(userMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 实例化自己的注解解析器
     * @return
     */
    @Bean
    public UmMethodArgumentResolver umMethodArgumentResolver() {
        return new UmMethodArgumentResolver();
    }

    @Bean
    public UserMethodArgumentResolver userMethodArgumentResolver() {
        return new UserMethodArgumentResolver();
    }

}
