
/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x; // x-coordinate of this point
    private final int y; // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x
     *            the <em>x</em>-coordinate of the point
     * @param y
     *            the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to
     * standard draw.
     *
     * @param that
     *            the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally,
     * if the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0)
     * / (x1 - x0). For completeness, the slope is defined to be +0.0 if the
     * line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and
     * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that
     *            the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        double slope;

        if (this.x == that.x && this.y == that.y) {
            slope = Double.NEGATIVE_INFINITY;
        } else if (that.x == this.x) {
            slope = Double.POSITIVE_INFINITY;
        } else if (that.y == this.y) {
            slope = +0.0;
        } else {
            slope = (double)(that.y - this.y) / (double)(that.x - this.x);
        }

        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that
     *            the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point
     *         (x0 = x1 and y0 = y1); a negative integer if this point is less
     *         than the argument point; and a positive integer if this point is
     *         greater than the argument point
     */
    public int compareTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return 0;
        } else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point. The slope is
     * defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        Comparator<Point> pointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.compareTo(o2);
            }
        };
        return pointComparator;
    }

    /**
     * Returns a string representation of this point. This method is provide for
     * debugging; your program should not rely on the format of the string
     * representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p1dup = new Point(1, 1);
        Point p3 = new Point(2, 1);

        assert p1.compareTo(p1) == 0;
        assert p1.compareTo(p2) == -1;
        assert p1.compareTo(p1dup) == 0;
        assert p1.compareTo(p3) == -1;
        assert p2.compareTo(p3) == 1;
        
        Point p4 = new Point(-1, -1);
        Point p5 = new Point(1, 1);
        Point p6 = new Point(-1, 1);
        Point p7 = new Point(1, -1);
        assert p4.compareTo(p5) == -1;
        assert p4.compareTo(p6) == -1;
        assert p4.compareTo(p7) == -1;
        assert p6.compareTo(p7) == 1;
        
        assert p1.slopeTo(p2) == Double.POSITIVE_INFINITY;
        assert p1.slopeTo(p1) == Double.NEGATIVE_INFINITY;
        assert p1.slopeTo(p3) == +0.0;
        assert p4.slopeTo(p5) == 1;
        assert p4.slopeTo(p2) == 1.5;
        assert p6.slopeTo(p7) == -1;
        
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        Point [] points2 = new Point[points.size()];
        points2 = points.toArray(points2);
        for(Point p : points2) {
            System.out.println(p);
        }
        Arrays.sort(points2, p1.slopeOrder());
        System.out.println("SORTED");
        for(Point p : points2) {
            System.out.println(p);
        }
        
        Point p10 = new Point(1, 1);
        Point p11 = new Point(-10, -10);
        p10.drawTo(p11);
        
    }
}