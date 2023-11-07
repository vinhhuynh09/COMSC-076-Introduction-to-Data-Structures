/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #7 Efficient Algorithms
 * Course: COMSC 076 Section 201. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: July 8, 2023
 ***********************************************************************************************************************
 * Description:
 (Closest pair of points) Section 22.8 introduced the following algorithm for finding the closest pair of points using
 a divide-and-conquer approach:

 Step 1: Sort the points in increasing order of x-coordinates. For the points with the same x-coordinates,
 sort on y-coordinates. The result should be a sorted collection of points denoted by S.

 Step 2: Divide S into two subsets, S1 and S2, of equal size about the midpoint of the sorted list S.
 Include the midpoint in S1. Recursively find the closest pair in S1 and S2. Let d1 and d2 denote the distance
 of closest pairs in the two subsets, respectively.

 Step 3: Find the closest pair between a point in S1 and a point in S2 and denote the distance between them as d3.
 The closest pair is the one with distance equal to the minimum of {d1, d2, d3}.

 The Algorithm for Obtaining stripL and stripR is:
 for each point p in pointsOrderedOnY
 if (p is in S1 and mid.x - p.x  <= d)
 append p to stripL;
 else if (p is in S2 and p.x - mid.x <= d)
 append p to stripR;

 Algorithm for Finding the Closest Pair in Step 3
 d = min(d1, d2);
 r = 0;  // r is the index of a point in stripR
 for (each point p in stripL) {
 // Skip the points in stripR below p.y - d
 while (r < stripR.length && q[r].y <= p.y - d)
 r++;
 let r1 = r;
 while (r1 < stripR.length && |q[r1].y  - p.y| <=  d) {
 // Check if (p, q[r1] is a possible closest pair
 if (distance(p, q[r1]) < d) {
 d = distance(p, q[r1]);
 (p, q[r1]) is now the current closest pair;
 }
 r1 = r1 + 1;
 }
 }

 Implement the algorithm to meet the following requirements:
 •	Define the classes Point and CompareY in the same way as your program from Chapter 20 for sorting points on both the x and y coordinates.
 •	Define  a class named Pair with data fields p1 and p2 to represent two points and a method named getDistance() that returns the distance between the two points.
 •	Implement the following methods:

 // Return the distance of the closest pair of points
 public static Pair getClosestPair(double [ ] [ ]  points)

 // Return the distance of the closest pair of points
 public static Pair getClosestPair(Point [ ]  points)

 // Return the distance of the closest pair of points in pointsOrderedOnX[low, high].
 // This is a recursive method. pointsOrderedOnX and pointsOrderedOnY are not changed in the subsequent recursive calls.
 public static Pair distance(Point [ ] pointsOrderedOnX, int low, int high, Point [ ] pointsOrderedOnY)

 //Compute the distance between two points p1 and p2
 public static double distance(Point p1, Point p2)

 //Compute the distance between points (x1, y1) and (x2, y2)
 public static double distance(double x1, double y1, double x2, double y2)

 The following is an example of the minimum expected output from your program:
 The shortest distance is 0.09201132727109172 beween the points
 (61.29956987796068, 46.22889070417251) and (61.30991147912446, 46.13746239547352)
 Time spent on the divide-and-conquer algorithm is 148 milliseconds

 You can also list the original 100 random points that were randomly generated at the beginning of the program
 before printing this summary data if you want. I also ran and timed the brute-force algorithm from Chapter 8 of the
 course textbook and got an execution time of 17 milliseconds. A question for you to consider: why did the
 divide-and-conquer approach require more time than the less complicated brute-force method (which is O(n^2) in this case?

 **********************************************************************************************************************/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
//import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        final int arraySize = 100;
        Point[] pointArray = new Point[arraySize];
        Point[] pointArray2 = new Point[arraySize];
        long startTime, endTime, executionTime;

        for (int i = 0; i < pointArray.length; i++) {   // randomly create arraySize points of values between [0, 100]
            pointArray[i] = new Point((Math.random() * 100), (Math.random()) * 100);
        }
        pointArray2 = pointArray.clone();  // clone pointArray to pointArray2

        System.out.println("Generated " + arraySize + " Points.");

        for(Point p: pointArray) {  // print all the random points generated
            System.out.println(p);
        }

        startTime = System.currentTimeMillis();
        Pair pair1 = Pair.getClosestPair(pointArray);  // find the shortest distance between 2 pairs of points
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        System.out.println();
        System.out.print(arraySize + " Points.");
        System.out.println("\nThe shortest distance is " + pair1.getDistance() + " between the points " + pair1.getP1() + " and " + pair1.getP2() );
        System.out.println("Time spent on the divide-and-conquer algorithm is " + executionTime + " milliseconds");

        double[][] d = new double[arraySize][2];
        d = convertPointToTwoDimensionalArray(pointArray2, arraySize);

        startTime = System.currentTimeMillis();
        Pair pair7 = Pair.getClosestPair(d);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("\nThe shortest distance is " + pair7.getDistance() + " between the points " + pair7.getP1() + " and " + pair7.getP2() );
        System.out.println("Time spent on the divide-and-conquer algorithm is " + executionTime + " milliseconds");

        /* TESTING 2 DIMENSIONAL ARRAY
        System.out.println("\nTest with 2-dimensional array:");

        //    for (int i = 0; i < twoDimensionalArray.length; i++) {
        //        System.out.println("("+ twoDimensionalArray[i][0] +" , "+ twoDimensionalArray[i][1]+")");
        //    }

        startTime = System.currentTimeMillis();

        Pair pair2 = Pair.getClosestPair(twoDimensionalArray);
        //   Pair pair2 = Pair.getClosestPair(pointArray2);
        endTime = System.currentTimeMillis();
        //   executionTime = endTime - startTime;

        System.out.println("\nThe shortest distance is " + pair2.getDistance() + " between the points " + pair2.getP1() + " and " + pair2.getP2() );
        System.out.println("Time spent on the divide-and-conquer algorithm is " + (endTime - startTime) + " milliseconds");
        */

    }  // end main

    public static double[][] convertPointToTwoDimensionalArray(Point p[], int arraySize) {
        // Convert pointArray to two-dimensional array
        double twoDimensionalArray[][] = new double[arraySize][2];
        for (int i = 0; i < p.length; i++) {
            twoDimensionalArray[i][0] = p[i].getX();
            twoDimensionalArray[i][1] = p[i].getY();
        }
        return twoDimensionalArray;
    }   // end public static double[][] convertPointToTwoDimensionalArray(Point p)
}  // end Main class


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

/********************************************************************************
 * class Pair
 ********************************************************************************/
class Pair {
    private Point p1;  // first x and y coordinate point
    private Point p2;  // second x and y coordinate point

    // Constructor
    public Pair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    // returns the distance between the two points
    public double getDistance() {
        return distance(p1, p2);
    }

    /**************************************************************************
     * public static double distance(Point p1, Point p2)
     * Description: Calculate the distance between two points.
     *     This method calls its overload method distance(double x1, double y1, double x2, double y2)
     *     Input: (Point p1, Point p2)
     *     Return: Distance between two points
     **************************************************************************/
    public static double distance(Point p1, Point p2) {
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }   // end public static double distance(Point p1, Point p2)

    /**************************************************************************
     * public static double distance(double x1, double y1, double x2, double y2)
     * Description: Calculate the distance between two points.
     *     This is a help method for distance(Point p1, Point p2)
     *     Input: (double x1, double y1, double x2, double y2)
     *     Return: Distance between two points using quadratic formula
     **************************************************************************/
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt( ((x2 -x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));  // formula for distance between 2 points
    }   // end public static double distance(double x1, double y1, double x2, double y2)


    /**************************************************************************
     * public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY)
     * Description: Calculate the distance between two points.
     *     This is a help method for distance(Point p1, Point p2)
     *     Input: (double x1, double y1, double x2, double y2)
     *     Return: Distance between two points using quadratic formula
     **************************************************************************/
    /** Return the distance of the closest pair of points in pointsOrderedOnX[low, high]. This is a recursive method.
     pointsOrderedOnX and pointsOrderedOnY are not changed in the subsequent recursive calls. **/
    public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
        int midPoint;
        double pointsDistance = 0;  // distance between 2 points
        Pair point = null;
        ArrayList<Point> stripL = new ArrayList<Point>();
        ArrayList<Point> stripR = new ArrayList<Point>();
        Pair leftPair, rightPair;

        if (low >= high) {  // base case
            return null;
        }
        else if (low + 1 == high) {  // base case
            return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
        }

        midPoint = (low + high) / 2; //divide the points into 2 subsets

        // calls function recursive until low >= high or low + 1 == high and returns the closest pair on the left
        // and right subsets
        leftPair = distance(pointsOrderedOnX, low, midPoint, pointsOrderedOnY);
        rightPair = distance(pointsOrderedOnX, midPoint + 1, high, pointsOrderedOnY);

        if (leftPair == null && rightPair == null) {    // are both pairs NULL?
            pointsDistance = 0;
            point = null;
        }
        else if (leftPair == null) {                    // is left pair NULL?
            pointsDistance = rightPair.getDistance();   // get distance from the right
            point = rightPair;
        }
        else if (rightPair == null) {                   // // is right pair NULL?
            pointsDistance = leftPair.getDistance();    // get distance from the left
            point = leftPair;
        }
        else {   // calculate the shortest distance between left and right
            pointsDistance = Math.min(leftPair.getDistance(), rightPair.getDistance());
            point = (leftPair.getDistance() <= rightPair.getDistance()) ? leftPair : rightPair;
        }

        /* From assignment's instructions:
        The Algorithm for Obtaining stripL and stripR is:
        for each point p in pointsOrderedOnY
            if (p is in S1 and mid.x - p.x  <= d)
                append p to stripL;
            else if (p is in S2 and p.x - mid.x <= d)
            append p to stripR;
        */

        for (int i = 0; i < pointsOrderedOnY.length; i++) {
            if ((pointsOrderedOnY[i].getX() <= pointsOrderedOnX[midPoint].getX())&&
                    (pointsOrderedOnX[midPoint].getX() - pointsOrderedOnY[i].getX() <= pointsDistance)) {
                stripL.add(pointsOrderedOnY[i]);
            }
            else if ((pointsOrderedOnY[i].getX() > pointsOrderedOnX[midPoint].getX()) &&
                    (pointsOrderedOnY[i].getX() - pointsOrderedOnX[midPoint].getX() <= pointsDistance)) {
                stripR.add(pointsOrderedOnY[i]);
            }
        }

        int indexStripR = 0;  // index of a point in stripR
        for (int i = 0; i < stripL.size(); i++) {
            while (indexStripR < stripR.size() && stripR.get(indexStripR).getY() <= stripL.get(i).getY() - pointsDistance) {
                indexStripR++;
            }
            int r1 = indexStripR;
            while (r1 < stripR.size() && Math.abs(stripR.get(r1).getY() - stripL.get(i).getY()) <=  pointsDistance) {
                // check if stripL.get(i), stripR.get(r1) is possibly the closest pair
                if (distance(stripL.get(i), stripR.get(r1)) < pointsDistance) {
                    pointsDistance = distance(stripL.get(i), stripR.get(r1));
                    // assign as the closest pair
                    point.p1 = stripL.get(i);
                    point.p2 = stripR.get(r1);
                }
                r1++;
            }  // end while loop
        }  // end for loop
        return point;
    }  // end public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY)

    /**************************************************************************
     * public static Pair getClosestPair(Point[] points)
     * Description: Calculate and return the closest distance between two points.
     * Input: Point[]
     * Return: distance between two points.
     **************************************************************************/
    public static Pair getClosestPair(Point[] points) {
        Arrays.sort(points);                            // sort increasingly by x-coordinate points
        Point[] pointsOrderedOnY = points.clone();      // clone after sorted point on x-coordinate
        Arrays.sort(pointsOrderedOnY, new CompareY());  // sort increasingly by y-coordinate points
        return distance(points, 0, points.length - 1, pointsOrderedOnY);
    }   // end getClosestPair(Point[] points)

    /**************************************************************************
     * public static Pair getClosestPair(double[][] points)
     *    Per requirement, user can pass in 2-dimensional array instead of points
     *    Convert from 2-dimensional arrays to points and call getClosestPair(Point[] points)
     **************************************************************************/
    public static Pair getClosestPair(double[][] points) { // for cases that involve 2-dimensional arrays
        Point[] points2 = new Point[points.length];

        for (int i = 0; i < points.length; i++) {   // convert 2-dimensional arrays to point
            points2[i] = new Point(points[i][0], points[i][1]);
        }
        return getClosestPair(points2);
    }   // end getClosestPair(double[][] points)
}  // end Pair class