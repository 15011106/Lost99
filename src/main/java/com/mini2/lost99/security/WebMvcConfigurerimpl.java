package com.mini2.lost99.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfigurerimpl  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080") //CORS

                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
                .allowCredentials(true);
    }

}
