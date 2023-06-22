package org.curious.code.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.jpa")
//@ConditionalOnProperty(name = "server.port",havingValue = "8181")
@Data
public class NewPropConfig {
    private String showSql;
    private String databasePlatform;
}
