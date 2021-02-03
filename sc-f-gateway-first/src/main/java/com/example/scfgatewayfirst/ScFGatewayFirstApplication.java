package com.example.scfgatewayfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScFGatewayFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScFGatewayFirstApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/meteor_93")
                        .uri("https://blog.csdn.net"))
                .build();
    }

}
