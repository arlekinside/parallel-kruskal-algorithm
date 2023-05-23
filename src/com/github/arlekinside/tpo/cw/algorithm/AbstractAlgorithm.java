package com.github.arlekinside.tpo.cw.algorithm;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.util.ProcessingResult;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public abstract class AbstractAlgorithm implements Algorithm {

    @Override
    public final ProcessingResult run(List<Edge> edges, int verticesNum) {
        before(edges, verticesNum);
        var start = Instant.now();

        var result = process(edges, verticesNum);

        var end = Instant.now();
        var duration = Duration.between(start, end);

        var processingResult = new ProcessingResult(result, duration);
        after(processingResult, verticesNum);

        return processingResult;
    }

    protected abstract List<Edge> process(List<Edge> edges, int verticesNum);

    protected void before(List<Edge> edges, int verticesNum) {}

    protected void after(ProcessingResult processingResult, int verticesNum) {
        System.out.printf("[%s] Vertices %s, edges size %s, time %sms,%n",
                this.getClass().getSimpleName(), verticesNum, processingResult.edges().size(), processingResult.duration().toMillis()
        );
    }
}
