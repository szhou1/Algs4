import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] input) {
        if (input == null) {
            throw new NullPointerException();
        }

        Point[] points = Arrays.copyOf(input, input.length);
        Arrays.sort(points);

        // check for duplicate points
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1].slopeTo(points[i]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }

        lineSegments = new ArrayList<LineSegment>();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
            Point origin = points[i];

            Point[] temp = Arrays.copyOf(points, points.length);

            Arrays.sort(temp, origin.slopeOrder());

            int counter = 1;
            Point min = origin;
            Point max = origin;

            double slope = 0;
            if (temp.length > 3) {
                slope = origin.slopeTo(temp[1]);
            }

            for (int q = 2; q < points.length; q++) {
                double slope2 = origin.slopeTo(temp[q]);
                if (slope == slope2) {
                    counter++;
                    max = temp[q];
                    min = min.compareTo(temp[q]) > 0 ? temp[q] : min;
                    min = min.compareTo(temp[q - 1]) > 0 ? temp[q - 1] : min;

                } else {
                    if (counter >= 3 && min == origin) {
                        lineSegments.add(new LineSegment(origin, max));
                    }
                    counter = 1;
                    max = origin;
                    min = origin;
                }
                slope = slope2;

            }

            if (counter >= 3 && min == origin) {
                lineSegments.add(new LineSegment(origin, max));
            }

        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
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

        FastCollinearPoints brute = new FastCollinearPoints(points);
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
