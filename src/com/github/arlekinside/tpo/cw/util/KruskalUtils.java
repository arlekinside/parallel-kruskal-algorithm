package com.github.arlekinside.tpo.cw.util;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.algorithm.parallel.forkjoin.EdgesSortRecursiveAction;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public final class KruskalUtils {

    private static final Random RANDOM = new SecureRandom();

    public static List<Edge> runKruskal(List<Edge> edges, int verticesNum) {
        var minSpanningTree = new ArrayList<Edge>();
        var unionFind = new UnionFind(verticesNum);

        QuickSortUtils.quickSort(edges, 0, edges.size() - 1);

        for (Edge edge : edges) {
            int src = edge.src();
            int dest = edge.dest();
            if (unionFind.find(src) != unionFind.find(dest)) {
                minSpanningTree.add(edge);
                unionFind.union(src, dest);
            }
        }

        return minSpanningTree;
    }

    public static List<Edge> runParSortKruskal(List<Edge> edges, int verticesNum, ForkJoinPool forkJoinPool) {
        var minSpanningTree = new ArrayList<Edge>();
        var unionFind = new UnionFind(verticesNum);

        forkJoinPool.invoke(new EdgesSortRecursiveAction(edges));

        for (Edge edge : edges) {
            int src = edge.src();
            int dest = edge.dest();
            if (unionFind.find(src) != unionFind.find(dest)) {
                minSpanningTree.add(edge);
                unionFind.union(src, dest);
            }
        }

        return minSpanningTree;
    }

    public static List<Edge> filter(List<Edge> edges, int verticesNum) {
        var filteredEdges = new ArrayList<Edge>();
        var unionFind = new UnionFind(verticesNum);

        for (Edge edge : edges) {
            int src = edge.src();
            int dest = edge.dest();
            if (unionFind.find(src) != unionFind.find(dest)) {
                filteredEdges.add(edge);
                unionFind.union(src, dest);
            }
        }

        return filteredEdges;
    }

    public static int calculateThreshold(List<Edge> edges) {
        var edgesNum = edges.size();
        if (edgesNum == 0) {
            return 1;
        }
        var comparativeValue = edges.get(0).weight();
        for (int i = 1; i < edgesNum; i++) {
            if (edges.get(i).weight() != comparativeValue) {
                return (int) (edgesNum * Math.log(edgesNum) / 2);
            }
        }
        return edgesNum + 1;
    }

    public static Edge choosePivot(List<Edge> edges) {
        var median = calculateVetRand(edges);
        median.sort(Edge::compareTo);
        return median.get((median.size() - 1) / 2);
    }

    public static List<Edge> calculateVetRand(List<Edge> vet) {
        var k = vet.size();
        var x = RANDOM.nextInt(k);

        var result = new ArrayList<Edge>();
        var used = new HashSet<Integer>();

        for (int i = 0; i * i < k; i++) {
            while (used.contains(x)) {
                x = RANDOM.nextInt(k);
            }
            used.add(x);
            result.add(vet.get(x));
        }

        return result;
    }

}
