package com.data.integration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
public class CamaConfig {

    public static String url;
    public static String username;
    public static String password;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        CamaConfig.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CamaConfig.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CamaConfig.password = password;
    }
}
