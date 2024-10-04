package com.ar.gfstabile.orders.configuration;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

    @Value("${external.service.product.timeout}")
    private Long productsTimeout;
    
    @Value("${external.service.people.timeout}")
    private Long peopleTimeout;

    @Bean("productsHttpClient")
    public HttpClient productsHttpClient() {
        return HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(productsTimeout))
                .build();
    }

    @Bean("peopleHttpClient")
    public HttpClient peopleHttpClient() {
        return HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(peopleTimeout))
                .build();
    }
}
