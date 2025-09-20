/*
 * MergeSort.java
 *
 * MergeSort with reusable buffer and insertion sort cutoff.
 */

public class MergeSort {
    private final int CUTOFF = 16; // use insertion sort for small arrays
    private Metrics metrics;
    private int[] buffer;

    public MergeSort(Metrics m) {
        this.metrics = m;
    }

    public void sort(int[] a) {
        if (a == null || a.length <= 1) return;
        buffer = new int[a.length];
        sortRec(a, 0, a.length - 1);
    }

    private void sortRec(int[] a, int l, int r) {
        if (r - l <= CUTOFF) {
            Utils.insertionSort(a, l, r, metrics);
            return;
        }
        metrics.enterRecursion();
        int mid = (l + r) / 2;
        sortRec(a, l, mid);
        sortRec(a, mid + 1, r);
        merge(a, l, mid, r);
        metrics.exitRecursion();
    }

    private void merge(int[] a, int l, int mid, int r) {
        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
            metrics.incComparisons();
            if (a[i] <= a[j]) buffer[k++] = a[i++];
            else buffer[k++] = a[j++];
        }
        while (i <= mid) buffer[k++] = a[i++];
        while (j <= r) buffer[k++] = a[j++];
        for (int x = l; x <= r; x++) a[x] = buffer[x];
    }
}
