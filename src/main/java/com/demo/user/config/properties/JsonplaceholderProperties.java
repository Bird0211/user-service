package com.demo.user.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jsonplaceholder")
public class JsonplaceholderProperties {

    String listUserUrl;

    String userPostUrl;

}
