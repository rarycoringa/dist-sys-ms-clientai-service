package br.edu.ufrn.clientai.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "openai")
public class OpenAIProperties {
    private String model;

    public OpenAIProperties() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
