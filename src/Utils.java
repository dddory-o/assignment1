import java.util.Random;

public class Utils {
    private static final Random rnd = new Random();

    public static void swap(int[] a, int i, int j, Metrics m) {
        if (i == j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        if (m != null) m.incSwaps();
    }

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; --i) {
            int j = rnd.nextInt(i + 1);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static void insertionSort(int[] a, int l, int r, Metrics m) {
        for (int i = l + 1; i <= r; ++i) {
            int key = a[i];
            int j = i - 1;
            while (j >= l && a[j] > key) {
                if (m != null) m.incComparisons();
                a[j + 1] = a[j];
                if (m != null) m.incSwaps();
                j--;
            }
            a[j + 1] = key;
        }
    }
}
