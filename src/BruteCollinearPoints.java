import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lineSegments;
    private int count = 0;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if(points == null) {
            throw new NullPointerException();
        }

        int n = points.length;
        lineSegments = new ArrayList<LineSegment>();
        Arrays.sort(points);
        for(int i = 0; i < n; i++) {
            if(points[i] == null) {
                throw new NullPointerException();
            }
        }

//        printArray(points);

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                // check for duplicate points
                if(points[p].slopeTo(points[q]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                
                for (int r = q + 1; r < n; r++) {
                    for (int s = r + 1; s < n; s++) {

                        double slopePQ = points[p].slopeTo(points[q]);
                        double slopeQR = points[q].slopeTo(points[r]);
                        double slopeRS = points[r].slopeTo(points[s]);
                        
                        
                        // double slopeSP = points[s].slopeTo(points[p]);
//                        System.out.println(points[p] + " " + points[q] + " " + points[r] + " " + points[s]);
                        if (slopePQ == slopeQR && slopePQ == slopeRS) {
//                            System.out.println("Same Slope");
                            LineSegment newSeg = new LineSegment(points[p], points[s]);
                            LineSegment newSegRev = new LineSegment(points[s], points[p]);
                            boolean exists = false;
                            for (LineSegment seg : lineSegments) {
                                if (seg != null) {
                                    if (seg.toString().equals(newSeg.toString())
                                            || seg.toString().equals(newSegRev.toString())) {
                                        exists = true;
                                    }
                                }
                            }
                            if (exists == false) {
                                count++;
                                lineSegments.add(newSeg);
                            }
                        }
                    }
                }
            }
        }

        // remove subsegments
        for (LineSegment seg : lineSegments) {

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

    private void printArray(Object[] array) {
        for (Object o : array) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        int k = StdIn.readInt();

        Point[] points = new Point[k];
        int count = 0;
        while (!StdIn.isEmpty() && count < k) {
            Point p = new Point(StdIn.readInt(), StdIn.readInt());
            points[count++] = p;
        }

        if(points.length < 4) {
            return;
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
        System.out.println("Total segments: " + brute.numberOfSegments());
        for (LineSegment ls : brute.segments()) {
            if (ls != null) {
//                System.out.println(ls);
                ls.draw();
            }
        }
        StdDraw.show();
    }
}
