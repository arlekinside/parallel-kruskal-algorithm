package com.github.arlekinside.tpo.cw;

public record Edge(int src, int dest, int weight) implements Comparable<Edge> {

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return (src == edge.src && dest == edge.dest) || (src == edge.dest && dest == edge.src);
    }
}
