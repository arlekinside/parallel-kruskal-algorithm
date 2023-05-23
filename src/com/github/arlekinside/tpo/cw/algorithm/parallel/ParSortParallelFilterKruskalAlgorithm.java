package com.github.arlekinside.tpo.cw.algorithm.parallel;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.algorithm.AbstractAlgorithm;
import com.github.arlekinside.tpo.cw.algorithm.parallel.forkjoin.ParSortFilterKruskalRecursiveTask;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParSortParallelFilterKruskalAlgorithm extends AbstractAlgorithm {

    protected final ForkJoinPool forkJoinPool;

    public ParSortParallelFilterKruskalAlgorithm(ForkJoinPool forkJoinPool) {
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    protected List<Edge> process(List<Edge> edges, int verticesNum) {
        return forkJoinPool.invoke(new ParSortFilterKruskalRecursiveTask(
                edges,
                verticesNum,
                forkJoinPool
        ));
    }
}
