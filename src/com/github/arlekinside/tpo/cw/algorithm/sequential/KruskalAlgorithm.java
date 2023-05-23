package com.github.arlekinside.tpo.cw.algorithm.sequential;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.algorithm.AbstractAlgorithm;
import com.github.arlekinside.tpo.cw.util.KruskalUtils;

import java.util.List;

public class KruskalAlgorithm extends AbstractAlgorithm {

    @Override
    protected List<Edge> process(List<Edge> edges, int verticesNum) {
        return KruskalUtils.runKruskal(edges, verticesNum);

    }

}
