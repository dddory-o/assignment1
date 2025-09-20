/*
 * QuickSort.java
 *
 * Randomized pivot, recurse on smaller partition first.
 */

import java.util.Random;

public class QuickSort {
    private Metrics metrics;
    private Random rnd = new Random();

    public QuickSort(Metrics m) {
        this.metrics = m;
    }

    public void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(int[] a, int l, int r) {
        while (l < r) {
            metrics.enterRecursion();
            int p = partition(a, l, r);
            if (p - l < r - p) {
                quickSort(a, l, p - 1);
                l = p + 1;
            } else {
                quickSort(a, p + 1, r);
                r = p - 1;
            }
            metrics.exitRecursion();
        }
    }

    private int partition(int[] a, int l, int r) {
        int pivot = a[l + rnd.nextInt(r - l + 1)];
        int i = l, j = r;
        while (i <= j) {
            while (a[i] < pivot) { metrics.incComparisons(); i++; }
            while (a[j] > pivot) { metrics.incComparisons(); j--; }
            if (i <= j) {
                Utils.swap(a, i, j, metrics);
                i++;
                j--;
            }
        }
        return i - 1;
    }
}
