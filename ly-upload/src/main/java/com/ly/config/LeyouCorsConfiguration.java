package com.ly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName: CorsConfiguration
 * @Author: Bolon
 * @Date: 2019/12/8 10:51
 * @Description: 跨域请求配置类
 * Cors跨域原理：（预检）请求到这里,并由此判断是否允许跨域访问,若允许才发起真正的请求访问
 */
@Configuration
public class LeyouCorsConfiguration {
    @Bean
    public CorsFilter corsFilter() {
        System.out.println("corsFilter................");
        //1.添加CORS配置信息，初始化CORS配置对象
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://manager.leyou.com");
        // config.addAllowedOrigin("http://www.leyou.com");
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        // config.addAllowedMethod("OPTIONS");
        // config.addAllowedMethod("HEAD");
        // config.addAllowedMethod("GET");
        // config.addAllowedMethod("PUT");
        // config.addAllowedMethod("POST");
        // config.addAllowedMethod("DELETE");
        // config.addAllowedMethod("PATCH");
        // *代表所有的请求方法
        config.addAllowedMethod("*");
        // 4）允许的头信息，*表示允许携带所有头信息
        config.addAllowedHeader("*");
        // 5）此次预检有效时长,有效时长内无需预检直接跨域访问
        config.setMaxAge(3600L);

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}
