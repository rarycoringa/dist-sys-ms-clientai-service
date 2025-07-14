package br.edu.ufrn.clientai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.clientai.records.AskResponse;
import br.edu.ufrn.clientai.services.ClientAIService;

@RestController
public class ClientAIController {
    @Autowired
    private ClientAIService clientAIService;

    @GetMapping("/ask")
    public AskResponse ask(@RequestParam String input) {

        String output = clientAIService.ask(input);

        return new AskResponse(input, output);
    }
}
