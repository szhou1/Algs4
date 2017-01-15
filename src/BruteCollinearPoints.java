import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import wk2_queue.RandomizedQueue;

public class BruteCollinearPoints {

    LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        int n = points.length;
        lineSegments = new LineSegment[n];
        int count = 0;

        for (int p = 0; p < n; p++) {
            for (int q = 0; q < n; q++) {
                for (int r = 0; r < n; r++) {
                    for (int s = 0; s < n; s++) {
                        // make sure 4 points are not null
                        if(points[p] == null || points[q] == null || points[r] == null || points[s] == null) {
                            continue;
                        }
                        
                        // make sure 4 points are distinct
                        if(points[p].compareTo(points[q]) == 0 
                            || points[p].compareTo(points[r]) == 0
                            || points[p].compareTo(points[s]) == 0
                            || points[q].compareTo(points[r]) == 0
                            || points[q].compareTo(points[s]) == 0
                            || points[r].compareTo(points[s]) == 0) {
                                continue;
                            }
                        
                        double slopeQ = points[p].slopeTo(points[q]);
                        double slopeR = points[p].slopeTo(points[r]);
                        double slopeS = points[p].slopeTo(points[s]);

                        if (slopeQ == slopeR && slopeQ == slopeS) {
                            Point[] collPoints = new Point[4];
                            collPoints[0] = points[p];
                            collPoints[1] = points[q];
                            collPoints[2] = points[r];
                            collPoints[3] = points[s];
                            Arrays.sort(collPoints, points[p].slopeOrder());
                            // System.out.println(collPoints[0] + " -> " +
                            // collPoints[3]);
                            LineSegment newSeg = new LineSegment(collPoints[0], collPoints[3]);
                            LineSegment newSegRev = new LineSegment(collPoints[3], collPoints[0]);
                            boolean exists = false;
                            for (LineSegment seg : lineSegments) {
                                if (seg != null) {
                                    if (seg.toString() == newSeg.toString() || seg.toString() == newSegRev.toString()) {
                                        exists = true;
                                    }

                                }
                            }
                            if (exists == false) {
                                System.out.println(newSeg);
                                
                                lineSegments[count++] = newSeg;
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {
        int k = StdIn.readInt();

        Point[] points = new Point[k];
        int count = 0;
        while (!StdIn.isEmpty() && count < k) {
            Point p = new Point(StdIn.readInt(), StdIn.readInt());
            points[count++] = p;
        }

        for (Point p : points) {
            System.out.println(p);
        }
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        System.out.println(brute.numberOfSegments());
        for (LineSegment ls : brute.segments()) {
            System.out.println(ls);
        }
        // for (int i = 0; i < k; i++) {
        // StdOut.println(queue.dequeue());
        // }
    }
}
