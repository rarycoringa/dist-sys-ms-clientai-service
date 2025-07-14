package br.edu.ufrn.clientai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.clientai.records.AskRequest;
import br.edu.ufrn.clientai.records.AskResponse;
import br.edu.ufrn.clientai.services.ClientAIService;

@RestController
public class ClientAIController {
    @Autowired
    private ClientAIService clientAIService;

    @PostMapping("/ask")
    public AskResponse ask(@RequestBody AskRequest request) {

        String input = request.input();

        String output = clientAIService.ask(input);

        return new AskResponse(input, output);
    }
}
