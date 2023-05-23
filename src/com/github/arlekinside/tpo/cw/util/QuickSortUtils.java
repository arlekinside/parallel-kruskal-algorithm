package com.github.arlekinside.tpo.cw.util;

import com.github.arlekinside.tpo.cw.Edge;

import java.util.Collections;
import java.util.List;

public final class QuickSortUtils {

    public static void quickSort(List<Edge> list, int start, int end) {
        if (start < end) {
            var pivotIndex = partition(list, start, end);
            quickSort(list, start, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, end);
        }
    }

    public static int partition(List<Edge> list, int low, int high) {
        var pivot = list.get(high);
        var i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
