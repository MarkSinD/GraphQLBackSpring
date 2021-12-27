package com.example.graphQLSpingBack.properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonAvatarProperties {
    private final String location = "file:./filestorage/";

    /**
     * Аватар по умолчанию в месте, когда аватар еще не был настроен
     */
    private final String defaultAvatar = "default.png";

    /**
     * Максимальная ширина аватара, используемая для хранения
     */
    private final int maxWidth = 200;

    /**
     * Максимальная высота аватара, используемая для хранения
     */
    private final int maxHeight = 200;

    /**
     * Базовый домен, который будет использоваться для обслуживания изображений
     */
    private final String baseDomain = "http://localhost:8080";

    public Dimensions getMaxDimensions() {
        return new Dimensions(maxWidth, maxHeight);
    }

    public PersonAvatarProperties() {

    }

    public String getLocation() {
        return location;
    }

    public String getDefaultAvatar() {
        return defaultAvatar;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public String getBaseDomain() {
        return baseDomain;
    }
}
