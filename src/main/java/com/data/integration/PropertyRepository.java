package com.data.integration;

import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyRepository {

    @Bean
    DataSource dataSource() throws SQLException {

        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }
}
