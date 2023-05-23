package com.github.arlekinside.tpo.cw.util;

import com.github.arlekinside.tpo.cw.Edge;

import java.time.Duration;
import java.util.List;

public record ProcessingResult(List<Edge> edges, Duration duration) {
}
