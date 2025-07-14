package br.edu.ufrn.clientai.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ufrn.clientai.records.openai.ResponsesRequest;
import br.edu.ufrn.clientai.records.openai.ResponsesResponse;

@FeignClient(name = "openai", url = "${openai.api.url}")
public interface OpenAIApiClient {
    @PostMapping("/responses")
    ResponsesResponse responses(@RequestBody ResponsesRequest request);

}
