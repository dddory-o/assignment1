/*
 * DeterministicSelect.java
 *
 * Median-of-Medians (MoM5) deterministic selection algorithm.
 */

import java.util.Arrays;

public class DeterministicSelect {
    private Metrics metrics;

    public DeterministicSelect(Metrics m) {
        this.metrics = m;
    }

    public int select(int[] a, int k) {
        return selectRec(a, 0, a.length - 1, k);
    }

    private int selectRec(int[] a, int l, int r, int k) {
        if (l == r) return a[l];
        int pivot = medianOfMedians(a, l, r);
        int p = partition(a, l, r, pivot);
        int len = p - l + 1;
        if (k == len) return a[p];
        else if (k < len) return selectRec(a, l, p - 1, k);
        else return selectRec(a, p + 1, r, k - len);
    }

    private int partition(int[] a, int l, int r, int pivot) {
        int i = l;
        for (int j = l; j <= r; j++) {
            if (a[j] == pivot) { Utils.swap(a, j, r, metrics); break; }
        }
        for (int j = l; j < r; j++) {
            metrics.incComparisons();
            if (a[j] < pivot) { Utils.swap(a, i, j, metrics); i++; }
        }
        Utils.swap(a, i, r, metrics);
        return i;
    }

    private int medianOfMedians(int[] a, int l, int r) {
        if (r - l < 5) {
            Arrays.sort(a, l, r + 1);
            return a[(l + r) / 2];
        }
        int n = 0;
        for (int i = l; i <= r; i += 5) {
            int subR = Math.min(i + 4, r);
            Arrays.sort(a, i, subR + 1);
            int median = a[(i + subR) / 2];
            a[l + n] = median;
            n++;
        }
        return medianOfMedians(a, l, l + n - 1);
    }
}
