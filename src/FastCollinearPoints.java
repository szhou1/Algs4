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
        Arrays.sort(points);
//        printArray(points);
        lineSegments = new ArrayList<LineSegment>();
        
        for(int i = 0; i < points.length; i++) {
            Point origin = points[i];
            
            Point [] temp = Arrays.copyOf(points, points.length);
            
            Arrays.sort(temp, origin.slopeOrder());
//            System.out.println("************************* Sorted slope order for " + origin);
//            printArray(temp);
            
            int counter = 1;
            Point min = origin;
            Point max = origin;
            double slope = origin.slopeTo(temp[1]);
            
            for(int q = 2; q < points.length; q++) {
                double slope2 = origin.slopeTo(temp[q]);
//                System.out.println(slope2 + " " + temp[q]);
                if(slope == slope2) {
//                    System.out.println("Found same adj slope: " + slope + " with " + temp[q]);
                    counter++;
//                    System.out.println("min: " + min);
                    max = max.compareTo(temp[q]) < 0 ? temp[q] : max;
                    max = max.compareTo(temp[q-1]) < 0 ? temp[q-1] : max;
                    min = min.compareTo(temp[q]) > 0 ? temp[q] : min;
                    min = min.compareTo(temp[q-1]) > 0 ? temp[q-1] : min;
//                    System.out.println("min after: " + min);

                } else {
                    if(counter >= 3 && min == origin) {
//                        System.out.println("Counter size: " + counter);
//                        System.out.println(origin + " -> " + max);
                        lineSegments.add(new LineSegment(origin, max));
                    }
                    counter = 1;
                    max = origin;
                    min = origin;
                }
                slope = slope2;
                
            }
            
            if(counter >= 3 && min == origin) {
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
        LineSegment [] arr = new LineSegment[numberOfSegments()];
        return lineSegments.toArray(arr);
    }
    
    private void printArray(Object[] array) {
        for(Object o : array) {
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
        
        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();

        FastCollinearPoints brute = new FastCollinearPoints(points);
        System.out.println("Total segments: " + brute.numberOfSegments());
        for (LineSegment ls : brute.segments()) {
            if(ls != null) {                
                System.out.println(ls);
//                ls.draw();
            }
        }
//        StdDraw.show();
    }

}
