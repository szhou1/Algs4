import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import wk2_queue.RandomizedQueue;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;
    private int count = 0;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        int n = points.length;
        lineSegments = new LineSegment[10];
        Arrays.sort(points, points[0].slopeOrder());

        for (int p = 0; p < n; p++) {
            for (int q = p+1; q < n; q++) {
                for (int r = q+1; r < n; r++) {
                    for (int s = r+1; s < n; s++) {
                        
                        double slopePQ = points[p].slopeTo(points[q]);
                        double slopeQR = points[q].slopeTo(points[r]);
                        double slopeRS = points[r].slopeTo(points[s]);
                        double slopeSP = points[s].slopeTo(points[p]);

                        if (slopePQ == slopeQR && slopePQ == slopeRS && slopePQ == slopeSP) {

                            LineSegment newSeg = new LineSegment(points[p], points[s]);
                            LineSegment newSegRev = new LineSegment(points[s], points[p]);
                            boolean exists = false;
                            for (LineSegment seg : lineSegments) {
                                if (seg != null) {
                                    if (seg.toString().equals(newSeg.toString()) || seg.toString().equals(newSegRev.toString())) {
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
        return count;
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
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        for (Point p : points) {
            System.out.println(p);
        }
        BruteCollinearPoints brute = new BruteCollinearPoints(points);
        System.out.println("Total segments: " + brute.numberOfSegments());
        for (LineSegment ls : brute.segments()) {
            if(ls != null) {                
                System.out.println(ls);
                ls.draw();
            }
        }
        StdDraw.show();
        // for (int i = 0; i < k; i++) {
        // StdOut.println(queue.dequeue());
        // }
    }
}
