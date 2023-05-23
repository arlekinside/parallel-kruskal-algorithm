package com.github.arlekinside.tpo.cw.algorithm.parallel.forkjoin;

import com.github.arlekinside.tpo.cw.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import static com.github.arlekinside.tpo.cw.util.KruskalUtils.*;
public class ParSortFilterKruskalRecursiveTask extends RecursiveTask<List<Edge>> {

    private final List<Edge> edges;
    private final int verticesNum;
    private final int edgesNum;
    private final ForkJoinPool forkJoinPool;

    public ParSortFilterKruskalRecursiveTask(List<Edge> edges, int verticesNum, ForkJoinPool forkJoinPool) {
        this.edges = edges;
        this.verticesNum = verticesNum;
        this.edgesNum = edges.size();
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    protected List<Edge> compute() {
        if (edgesNum <= calculateThreshold(edges)) {
            return runParSortKruskal(Collections.synchronizedList(edges), verticesNum, forkJoinPool);
        }

        return invokeAll(getSubTasks())
                .stream()
                .map(ForkJoinTask::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ParSortFilterKruskalRecursiveTask> getSubTasks() {
        var subTasks = new ArrayList<ParSortFilterKruskalRecursiveTask>();

        var pivotEdge = choosePivot(edges);

        var lessThanPivot = new ArrayList<Edge>();
        var greaterThanPivot = new ArrayList<Edge>();

        for (int i = 1; i < edgesNum; i++) {
            var edge = edges.get(i);

            if (edge.weight() <= pivotEdge.weight()) {
                lessThanPivot.add(edge);
            } else {
                greaterThanPivot.add(edge);
            }
        }

        subTasks.add(new ParSortFilterKruskalRecursiveTask(lessThanPivot, verticesNum, forkJoinPool));

        var filteredGreaterThanPivot = filter(greaterThanPivot, verticesNum);
        subTasks.add(new ParSortFilterKruskalRecursiveTask(filteredGreaterThanPivot, verticesNum, forkJoinPool));

        return subTasks;
    }

}
