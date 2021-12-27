package com.example.graphQLSpingBack.util;

import com.example.graphQLSpingBack.properties.PersonAvatarProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class PersonAvatarConfig implements WebMvcConfigurer {
    private final PersonAvatarProperties properties;

    public PersonAvatarConfig(PersonAvatarProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/avatar/**")
                .addResourceLocations(properties.getLocation());
    }
}
