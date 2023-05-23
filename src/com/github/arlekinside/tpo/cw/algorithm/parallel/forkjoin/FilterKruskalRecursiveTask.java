package com.github.arlekinside.tpo.cw.algorithm.parallel.forkjoin;

import com.github.arlekinside.tpo.cw.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import static com.github.arlekinside.tpo.cw.util.KruskalUtils.*;
public class FilterKruskalRecursiveTask extends RecursiveTask<List<Edge>> {

    private final List<Edge> edges;
    private final int verticesNum;
    private final int edgesNum;

    public FilterKruskalRecursiveTask(List<Edge> edges, int verticesNum) {
        this.edges = edges;
        this.verticesNum = verticesNum;
        this.edgesNum = edges.size();
    }

    @Override
    protected List<Edge> compute() {
        if (edgesNum <= calculateThreshold(edges)) {
            return runKruskal(edges, verticesNum);
        }

        return invokeAll(getSubTasks())
                .stream()
                .map(ForkJoinTask::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<FilterKruskalRecursiveTask> getSubTasks() {
        var subTasks = new ArrayList<FilterKruskalRecursiveTask>();

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

        subTasks.add(new FilterKruskalRecursiveTask(lessThanPivot, verticesNum));

        var filteredGreaterThanPivot = filter(greaterThanPivot, verticesNum);
        subTasks.add(new FilterKruskalRecursiveTask(filteredGreaterThanPivot, verticesNum));

        return subTasks;
    }

}
