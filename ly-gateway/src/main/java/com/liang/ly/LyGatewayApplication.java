package com.liang.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Bolon
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class LyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyGatewayApplication.class, args);
    }

}
