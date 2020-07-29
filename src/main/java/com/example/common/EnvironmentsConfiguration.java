package com.example.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 環境変数を参照するクラス.
 * 
 * @author shuhei.iida
 *
 */
@Component
@ConfigurationProperties(prefix = "environments")
@Data
public class EnvironmentsConfiguration {

    /** CORSを許可するオリジンのURL */
    private String originUrl;

}