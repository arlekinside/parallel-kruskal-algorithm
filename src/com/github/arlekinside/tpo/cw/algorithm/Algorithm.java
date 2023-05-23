package com.github.arlekinside.tpo.cw.algorithm;

import com.github.arlekinside.tpo.cw.Edge;
import com.github.arlekinside.tpo.cw.util.ProcessingResult;

import java.util.List;

public interface Algorithm {

    ProcessingResult run(List<Edge> edges, int verticesNum);

}
