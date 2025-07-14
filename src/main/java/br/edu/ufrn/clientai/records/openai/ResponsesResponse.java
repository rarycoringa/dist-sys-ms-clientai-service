package br.edu.ufrn.clientai.records.openai;

import java.util.List;

public record ResponsesResponse(
    String id,
    String model,
    List<Output> output
) {
    public record Output(List<Content> content) {}

    public record Content(String text) {}
}
