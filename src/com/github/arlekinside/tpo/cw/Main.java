package com.github.arlekinside.tpo.cw;

import com.github.arlekinside.tpo.cw.algorithm.PrintingAlgorithmDecorator;
import com.github.arlekinside.tpo.cw.algorithm.parallel.ParSortParallelFilterKruskalAlgorithm;
import com.github.arlekinside.tpo.cw.algorithm.parallel.ParallelFilterKruskalAlgorithm;
import com.github.arlekinside.tpo.cw.algorithm.parallel.ParallelKruskalAlgorithm;
import com.github.arlekinside.tpo.cw.algorithm.sequential.FilterKruskalAlgorithm;
import com.github.arlekinside.tpo.cw.algorithm.sequential.KruskalAlgorithm;
import com.github.arlekinside.tpo.cw.util.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool(4);
    private static final int VERTICES_NUM = 10000;
    private static final float DENSITY = 1f;

    public static void main(String[] args) {
    }

    private static void printSpeedUp(Duration seq, Duration par) {
        System.out.printf("SpeedUp %.2f%n", (seq.toNanos() + 0d) / par.toNanos());
    }

    private static List<Edge> getDefaultEdges() {
        var edges = new ArrayList<Edge>();

        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 7, 8));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 7, 11));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(2, 8, 2));
        edges.add(new Edge(2, 5, 4));
        edges.add(new Edge(3, 4, 9));
        edges.add(new Edge(3, 5, 14));
        edges.add(new Edge(4, 5, 10));
        edges.add(new Edge(5, 6, 2));
        edges.add(new Edge(6, 7, 1));
        edges.add(new Edge(6, 8, 6));
        edges.add(new Edge(7, 8, 7));

        return edges;
    }
}