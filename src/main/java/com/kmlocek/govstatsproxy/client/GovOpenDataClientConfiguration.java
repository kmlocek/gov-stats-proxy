package com.kmlocek.govstatsproxy.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GovOpenDataClientConfiguration {

    @Value("${gov.base-url}")
    String baseUrl;

    @Value("${gov.x-api-version}")
    String xApiVersion;

    @Bean
    public WebClient govOpenDataClient() {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-API-VERSION", xApiVersion)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
