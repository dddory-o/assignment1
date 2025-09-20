import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Metrics m = new Metrics();

        // Example: MergeSort
        int[] arr1 = {5, 2, 9, 1, 7, 3};
        MergeSort ms = new MergeSort(m);
        ms.sort(arr1);
        System.out.println("MergeSort: " + Arrays.toString(arr1));
        System.out.println(m.csvHeader());
        System.out.println(m.toCSV());

        // Example: QuickSort
        m.reset();
        int[] arr2 = {5, 2, 9, 1, 7, 3};
        QuickSort qs = new QuickSort(m);
        qs.sort(arr2);
        System.out.println("QuickSort: " + Arrays.toString(arr2));
        System.out.println(m.toCSV());

        // Example: Deterministic Select
        m.reset();
        int[] arr3 = {7, 10, 4, 3, 20, 15};
        DeterministicSelect ds = new DeterministicSelect(m);
        int kth = ds.select(arr3, 3);
        System.out.println("3rd smallest: " + kth);
        System.out.println(m.toCSV());

        // Example: Closest Pair
        ClosestPair2D cp = new ClosestPair2D();
        ClosestPair2D.Point[] pts = {
                new ClosestPair2D.Point(2, 3),
                new ClosestPair2D.Point(12, 30),
                new ClosestPair2D.Point(40, 50),
                new ClosestPair2D.Point(5, 1),
                new ClosestPair2D.Point(12, 10),
                new ClosestPair2D.Point(3, 4)
        };
        double d = cp.closestPair(pts);
        System.out.println("Closest pair distance: " + d);

        // Save results to CSV
        try (FileWriter fw = new FileWriter("results.csv")) {
            fw.write(m.csvHeader() + "\\n");
            fw.write(m.toCSV() + "\\n");
        }
    }
}
