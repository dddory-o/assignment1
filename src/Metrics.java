public class Metrics {
    private long comparisons = 0;
    private long swaps = 0;
    private int maxRecursionDepth = 0;
    private int currentDepth = 0;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        maxRecursionDepth = 0;
        currentDepth = 0;
    }

    public void incComparisons() {
        comparisons++;
    }

    public void incSwaps() {
        swaps++;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxRecursionDepth) {
            maxRecursionDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public String csvHeader() {
        return "comparisons,swaps,maxRecursionDepth";
    }

    public String toCSV() {
        return comparisons + "," + swaps + "," + maxRecursionDepth;
    }
}
