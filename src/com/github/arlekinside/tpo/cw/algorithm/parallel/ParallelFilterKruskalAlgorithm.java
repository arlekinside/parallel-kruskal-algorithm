package com.github.arlekinside.tpo.cw.algorithm.parallel;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.algorithm.AbstractAlgorithm;
import com.github.arlekinside.tpo.cw.algorithm.parallel.forkjoin.FilterKruskalRecursiveTask;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelFilterKruskalAlgorithm extends AbstractAlgorithm {

    protected final ForkJoinPool forkJoinPool;

    public ParallelFilterKruskalAlgorithm(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    protected List<Edge> process(List<Edge> edges, int verticesNum) {
        return forkJoinPool.invoke(new FilterKruskalRecursiveTask(
                        edges,
                        verticesNum
        ));
    }
}
