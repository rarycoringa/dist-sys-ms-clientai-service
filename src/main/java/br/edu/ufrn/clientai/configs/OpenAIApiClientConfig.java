package br.edu.ufrn.clientai.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ufrn.clientai.properties.OpenAIApiProperties;
import feign.RequestInterceptor;

@Configuration
public class OpenAIApiClientConfig {
    @Autowired
    private OpenAIApiProperties openAIAPIProperties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Authorization", "Bearer " + openAIAPIProperties.getKey());
        };
    }
}
