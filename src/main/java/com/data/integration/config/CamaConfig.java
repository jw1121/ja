package com.data.integration.config;

import org.springframework.beans.factory.annotation.Value;

public class CamaConfig {

    @Value("${spring.datasource.url}")
    public static String db_url;
    @Value("${spring.datasource.username}")
    public static String db_user;
    @Value("${spring.datasource.password}")
    public static String db_pw;

}
