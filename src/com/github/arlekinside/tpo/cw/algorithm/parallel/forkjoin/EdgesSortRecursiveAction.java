package com.github.arlekinside.tpo.cw.algorithm.parallel.forkjoin;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.util.QuickSortUtils;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class EdgesSortRecursiveAction extends RecursiveAction {

    private final List<Edge> edges;
    private final int start;
    private final int end;

    public EdgesSortRecursiveAction(List<Edge> edges) {
        this.edges = edges;
        this.start = 0;
        this.end = edges.size() - 1;
    }

    public EdgesSortRecursiveAction(List<Edge> edges, int start, int end) {
        this.edges = edges;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (start < end) {
            var pivotIndex = QuickSortUtils.partition(edges, start, end);

            var leftTask = new EdgesSortRecursiveAction(edges, start, pivotIndex - 1);
            var rightTask = new EdgesSortRecursiveAction(edges, pivotIndex + 1, end);

            invokeAll(leftTask, rightTask);
        }
    }
}
