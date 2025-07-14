package br.edu.ufrn.clientai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.clientai.clients.OpenAIApiClient;
import br.edu.ufrn.clientai.properties.OpenAIProperties;
import br.edu.ufrn.clientai.records.openai.ResponsesRequest;
import br.edu.ufrn.clientai.records.openai.ResponsesResponse;

@RestController
public class ClientAIController {
    @Autowired
    private OpenAIApiClient openAIApiClient;

    @Autowired
    private OpenAIProperties openAIProperties;

    @GetMapping("/restaurants")
    public String restaurants(@RequestParam String city) {
        String model = openAIProperties.getModel();

        String input = (
            "Tell me good restaurants to visit on " + city + "."
            + " Without formatting. Without markdown. Only pure text."
            + " Not too many words. Not introduction. Splitted by ;"
        );
        ResponsesRequest responsesRequest = new ResponsesRequest(model, input);
        ResponsesResponse responsesResponse = openAIApiClient.responses(responsesRequest);

        return responsesResponse.output().get(0).content().get(0).text();
    }
}
