package br.edu.ufrn.clientai.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.clientai.clients.OpenAIApiClient;
import br.edu.ufrn.clientai.properties.OpenAIProperties;
import br.edu.ufrn.clientai.records.openai.ResponsesRequest;
import br.edu.ufrn.clientai.records.openai.ResponsesResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class ClientAIService {
    private static final Logger logger = LoggerFactory.getLogger(ClientAIService.class);

    @Autowired
    private OpenAIApiClient openAIApiClient;

    @Autowired
    private OpenAIProperties openAIProperties;

    public String askFallback(String input, Throwable t) {
        logger.error(
            "Fallback askFallback() triggered due to exception."
        );

        String output = "Unavailable";

        return output;
    }

    @RateLimiter(name = "openai", fallbackMethod = "askFallback")
    @CircuitBreaker(name = "openai", fallbackMethod = "askFallback")
    @Bulkhead(name = "openai", fallbackMethod = "askFallback")
    public String ask(String input) {
        String model = openAIProperties.getModel();

        ResponsesRequest request = new ResponsesRequest(model, input);

        logger.info("Calling OpenAI with request {}", request.toString());

        ResponsesResponse response = openAIApiClient.responses(request);

        logger.info("Received response from OpenAI: {}", response.toString());

        String output = response.output().get(0).content().get(0).text(); 

        return output;
    }
}
