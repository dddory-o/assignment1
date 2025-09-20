import java.util.*;

public class ClosestPair2D {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    public double closestPair(Point[] pts) {
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        return closestRec(pts, 0, pts.length - 1);
    }

    private double closestRec(Point[] pts, int l, int r) {
        if (r - l <= 3) return bruteForce(pts, l, r);
        int m = (l + r) / 2;
        double d = Math.min(closestRec(pts, l, m), closestRec(pts, m + 1, r));
        return Math.min(d, stripClosest(pts, l, r, m, d));
    }

    private double bruteForce(Point[] pts, int l, int r) {
        double d = Double.MAX_VALUE;
        for (int i = l; i <= r; i++)
            for (int j = i + 1; j <= r; j++)
                d = Math.min(d, dist(pts[i], pts[j]));
        return d;
    }

    private double stripClosest(Point[] pts, int l, int r, int m, double d) {
        List<Point> strip = new ArrayList<>();
        double mid = pts[m].x;
        for (int i = l; i <= r; i++)
            if (Math.abs(pts[i].x - mid) < d) strip.add(pts[i]);
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++)
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++)
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
        return d;
    }

    private double dist(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }
}
