package com.github.arlekinside.tpo.cw.algorithm.sequential;

import com.github.arlekinside.tpo.cw.algorithm.AbstractAlgorithm;
import com.github.arlekinside.tpo.cw.Edge;

import java.util.ArrayList;
import java.util.List;

import static com.github.arlekinside.tpo.cw.util.KruskalUtils.*;

public class FilterKruskalAlgorithm extends AbstractAlgorithm {

    @Override
    protected List<Edge> process(List<Edge> edges, int verticesNum) {
        var edgesNum = edges.size();

        if (edgesNum <= calculateThreshold(edges)) {
            return runKruskal(edges, verticesNum);
        }

        var minSpanningTree = new ArrayList<Edge>();
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

        minSpanningTree.addAll(process(lessThanPivot, verticesNum));

        var filteredGreaterThanPivot = filter(greaterThanPivot, verticesNum);
        minSpanningTree.addAll(process(filteredGreaterThanPivot, verticesNum));

        return minSpanningTree;
    }

}
