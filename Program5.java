/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #5 Lists, Stacks, and Queues
 * Course: COMSC 076. Summer 2023
 * Date: July 5, 2023
 ***********************************************************************************************************************
 * Description:
 (Sort the points in a plane) Write a program that meets the following requirements:

 Define a class named Point with two data fields x and y to represent a point's x- and y-coordinates. Implement the
 Comparable interface for the comparing the points on x-coordinates. If two points have the same x-coordinates, compare
 their y-coordinates.

 Define a class named CompareY that implements Comparator<Point>. Implement the compare method to compare two points on
 their y-coordinates. If two points have the same y-coordinates, compare their x-coordinates.

 Randomly create 100 points and apply the Arrays.sort method to display the points in increasing order of their
 x-coordinates, and increasing order of their y-coordinates, respectively.
 **********************************************************************************************************************/

import java.util.Arrays;
import java.util.Comparator;
public class Program5 {
    public static void main(String[] args) {
        final int arraySize = 100;

        Point[] pointArray = new Point[arraySize];
        // The requirement ask for
        // If two points have the same x-coordinates, compare their y-coordinates.
        // It's impossible to have two same x coordinates using Math.random()
        // One way to test is to hardcode pointArray[0] and pointArray[1] with the same x coordinate
        // pointArray[0] =new Point(1.2345, 1.2345678);
        // pointArray[1] =new Point(1.2345, 2.3456789);
        // Also change for (int i = 2; i < pointArray.length; i++)

        for (int i = 0; i < pointArray.length; i++) {   // randomly create arraySize points of values between [0, 100)
            pointArray[i] = new Point((Math.random() * 100), (Math.random()) * 100);
        }

        Arrays.sort(pointArray);    // sort points in increasing order of x-coordinates

        System.out.println("\nPoints sorted on x-coordinates");
        for (Point p : pointArray) {    // print sorted points.
            System.out.println(p.toString());
        }

        Arrays.sort(pointArray, new CompareY());    // sort points in increasing order of y-coordinates

        System.out.println("\nPoints sorted on y-coordinates");
        for (Point p : pointArray) {    // print sorted points.
            System.out.println(p.toString());
        }
    }   // end main()
}   // end class

/**************************************************************************
 * class Point implements Comparable<Point>
 **************************************************************************/
class Point implements Comparable<Point> {
    private double x;  // x-coordinate point
    private double y;  // y-coordinate point

    /** DEFAULT CONSTRUCTOR **/
    Point() {
        x = 1.0;
        y = 1.0;
    }

    /** CONSTRUCTOR THAT TAKES IN X AND Y COORDINATE **/
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    /************************************************************************
     * public int compareTo(Point point)
     * Override the compareTo method in the Comparable class.
     * Compare 2 x-coordinate points. If two same x-coordinates, compare their y-coordinates
     * Input: Point
     * Return: 1, 0, or -1
     ***********************************************************************/
    @Override     // override the compareTo method in the Comparable class
    // compare 2 x-coordinate points
    public int compareTo(Point point) {
        if (x == point.getX()) {   // two points have the same x-coordinates, compare their y-coordinates
            if (y > point.getY()) {
                return 1;
            }
            return((y < point.getY()) ? -1 : 0);    // using Ternary Operator for if, else
        }
        return((x > point.getX()) ? 1 : -1);
    }   // end public int compareTo(Point point)

    /************************************************************************
     * public String toString()
     * Override the toString method in the Object class
     ***********************************************************************/
    @Override
    public String toString() {
        return "(" + String.format("%.14f", x) + ", " + String.format("%.14f", y) + ")";
    }
}   // end class Point implements Comparable<Point>

/************************************************************************
 * class CompareY implements Comparator<Point>
 ***********************************************************************/
class CompareY implements Comparator<Point> {
    /*******************************************************************
     * public int compare(Point p1, Point p2)
     * Compare 2 y-coordinate points. If two same y-coordinates, compare their x-coordinates
     * Input: Point p1, Point p2
     * Return: 1, 0, or -1
     ******************************************************************/
    public int compare(Point p1, Point p2) {
        if (p1.getY() == p2.getY()) {   // if two points have the same y-coordinates, compare their x-coordinates
            if (p1.getX() < p2.getX()) {
                return -1;
            }
            return ((p1.getX() == p2.getY())) ? 0 : 1;
        }
        return ((p1.getY() < p2.getY())) ? -1 : 1;
    }
}   // end class CompareY implements Comparator<Point>
