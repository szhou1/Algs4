import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments;
    private int count = 0;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] input) {
        if (input == null) {
            throw new NullPointerException();
        }
        Point[] points = Arrays.copyOf(input, input.length);

        int n = points.length;
        lineSegments = new ArrayList<LineSegment>();
        Arrays.sort(points);
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
        }

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                // check for duplicate points
                if (points[p].slopeTo(points[q]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }

                for (int r = q + 1; r < n; r++) {
                    for (int s = r + 1; s < n; s++) {

                        double slopePQ = points[p].slopeTo(points[q]);
                        double slopeQR = points[q].slopeTo(points[r]);
                        double slopeRS = points[r].slopeTo(points[s]);

                        if (slopePQ == slopeQR && slopePQ == slopeRS) {
                            LineSegment newSeg = new LineSegment(points[p], points[s]);
                            count++;
                            lineSegments.add(newSeg);
                        }
                    }
                }
            }
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] arr = new LineSegment[numberOfSegments()];
        return lineSegments.toArray(arr);
    }

    public static void main(String[] args) {
        int k = StdIn.readInt();

        Point[] points = new Point[k];
        int count = 0;
        while (!StdIn.isEmpty() && count < k) {
            Point p = new Point(StdIn.readInt(), StdIn.readInt());
            points[count++] = p;
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        StdOut.println("Total segments: " + brute.numberOfSegments());
        for (LineSegment ls : brute.segments()) {
            if (ls != null) {
                StdOut.println(ls);
                ls.draw();
            }
        }
        StdDraw.show();
    }
}
