package com.github.arlekinside.tpo.cw.algorithm;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.util.ProcessingResult;
import com.github.arlekinside.tpo.cw.util.Utils;

import java.util.List;

public final class PrintingAlgorithmDecorator extends AbstractAlgorithm {

    private final AbstractAlgorithm decorated;

    public PrintingAlgorithmDecorator(AbstractAlgorithm decorated) {
        this.decorated = decorated;
    }

    @Override
    protected List<Edge> process(List<Edge> edges, int verticesNum) {
        return decorated.process(edges, verticesNum);
    }

    @Override
    protected void before(List<Edge> edges, int verticesNum) {
        System.out.printf("""
                [%s] Started... Edges size %s
                Edges:
                %s
                """, decorated.getClass().getSimpleName(), edges.size(), Utils.toStringEdges(edges)
        );
    }

    @Override
    protected void after(ProcessingResult processingResult, int verticesNum) {
        System.out.printf("""
                [%s] Ended... Time %sms, edges size %s
                MST:
                %s
                """,
                decorated.getClass().getSimpleName(),
                processingResult.duration().toMillis(),
                processingResult.edges().size(),
                Utils.toStringEdges(processingResult.edges())
        );
    }
}
