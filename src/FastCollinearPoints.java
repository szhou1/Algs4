import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class FastCollinearPoints {
    
    private ArrayList<LineSegment> lineSegments;

    
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        Arrays.sort(points, points[0].slopeOrder());
        
        lineSegments = new ArrayList<LineSegment>();
        
        for(int p = 0; p < points.length; p++) {
            
//            List<Double> slopes = new ArrayList<Double>();
            
            for(int q = p+1; q < points.length; q++) {
                double slope = points[p].slopeTo(points[q]);
                
            }
            
            
            
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment [] arr = new LineSegment[numberOfSegments()];
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
        System.out.println("Total segments: " + brute.numberOfSegments());
        for (LineSegment ls : brute.segments()) {
            if(ls != null) {                
                System.out.println(ls);
                ls.draw();
            }
        }
        StdDraw.show();
    }

}
