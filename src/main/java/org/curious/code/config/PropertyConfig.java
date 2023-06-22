package org.curious.code.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
//@ConditionalOnProperty(name = "spring.datasource.username",havingValue = "sa")
@Data
public class PropertyConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Profile("dev")
    @Bean
    public String devSecurity(){
        System.out.println("devSecurity--");
        System.out.println("devSecurity-url-"+url);
        return "devSecurity";
    }

    @Profile("prod")
    @Bean
    public String prodSecurity(){
        System.out.println("prodSecurity--");
        System.out.println("prodSecurity-url-"+url);
        return "prodSecurity";
    }

}
