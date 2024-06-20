package com.boyan.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.jdbc")
public class DataSource2 {

    private String driverClassName;

    private String url;

    private String user;

    private String password;
}
