package com.github.arlekinside.tpo.cw.util;

import com.github.arlekinside.tpo.cw.Edge;

import java.security.SecureRandom;
import java.util.*;

public final class Utils {

    private static final Random RANDOM = new SecureRandom();

    public static String toStringEdges(List<Edge> edges) {
        var sb = new StringBuilder();
        for (Edge edge : edges) {
            sb.append(edge.src()).append(" - ").append(edge.dest()).append(": ").append(edge.weight()).append('\n');
        }
        return sb.toString();
    }

    public static <T> List<T> copyList(List<T> src) {
        var size = src.size();
        var res = new Object[size];

        System.arraycopy(src.toArray(), 0, res, 0, size);

        return (List<T>) Arrays.asList(res);
    }

    public static List<Edge> generateRandomGraph(int numVertices, int weightBound, float density) {
        var edges = new HashSet<Edge>();
        var edgesNum = computeMaxEdges(numVertices) * Math.min(1, density);

        for (int i = 0; i < edgesNum; i++) {
            var src = RANDOM.nextInt(numVertices);
            var dest = RANDOM.nextInt(numVertices);
            var weight = RANDOM.nextInt(weightBound) + 1;

            edges.add(new Edge(src, dest, weight));
        }

        return new ArrayList<>(edges);
    }

    public static int computeMaxEdges(int numOfVertices) {
        return numOfVertices * ((numOfVertices - 1) / 2);
    }
}
