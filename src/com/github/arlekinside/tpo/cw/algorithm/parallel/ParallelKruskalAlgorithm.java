package com.github.arlekinside.tpo.cw.algorithm.parallel;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.algorithm.AbstractAlgorithm;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static com.github.arlekinside.tpo.cw.util.KruskalUtils.*;

public class ParallelKruskalAlgorithm extends AbstractAlgorithm {

    protected final ForkJoinPool forkJoinPool;

    public ParallelKruskalAlgorithm(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    protected List<Edge> process(List<Edge> edges, int verticesNum) {
        return runParSortKruskal(edges, verticesNum, forkJoinPool);
    }
}
