/***********************************************************************************************************************
 * Name: Vinh Huynh
 * Assignment: Program #1 Abstract Classes and Interfaces
 * Course: COMSC 076 Section 201. Summer 2023
 * Instructor: Professor Henry Estrada
 * Date: June 21, 2023
 ***********************************************************************************************************************
 * Description:
 Design a new Triangle class that extends the abstract GeometricObject Download GeometricObjectclass. (The source code
 for this abstract class can be found in chapters 9, 11, & 13 among the source files for the text. Write a test program
 that prompts the user to enter three sides of the triangle (make sure they define an actual triangle), a color, and a
 Boolean value to indicate whether the triangle is filled. The program should then create a Triangle object with these
 sides and set the color and filled properties using the input. In addition, have the program make use of constructors
 to add circle and rectangle objects. The program should display, for each of these geometric objects, the area,
 perimeter, color, and true or false to indicate whether it is filled or not. Place all of the classes in a single Java
 source file named TestGeometricObjects.java. Make sure that the class that contains the main method is also named
 public class TestGeometricObjects, and that the Triangle, Circle, and Rectangle classes are not public.
 **********************************************************************************************************************/
import java.util.Scanner;

public class TestGeometricObjects {
        /*******************************************
         * Main method
         ******************************************/
        public static void main(String[] args) {
                Scanner input = new Scanner(System.in);
                double side1, side2, side3;
                String color, filled;
                boolean isFilled;

                for (;;) {  // continue to ask for valid triangle sizes until they are valid
                        System.out.print("Please enter the sides of a triangle: ");
                        side1 = input.nextDouble();
                        side2 = input.nextDouble();
                        side3 = input.nextDouble();
                        if (!isValidTriangle(side1, side2, side3)) {  // check for invalid triangle side lengths
                                System.out.println("Unable to create a triangle with those sides.");
                                System.out.println();
                        }
                        else {   // break out of for loop if triangle sizes are valid
                                break;
                        }
                }   // end for loop
                // By now we have valid triangle sizes. Ask user for color and filled.
                System.out.print("What is the color of the triangle: ");
                color = input.next();

                // ask for filled user input of triangle
                System.out.print("Is the color filled(true/false)?: ");
                filled = input.next();
                // Since isFilled is a boolean, the user must either enter "true" or "false" or else the program
                // will crash when the line isFilled = input.nextBoolean() is executed. Therefore, the code was
                // implemented in this manner to prevent the crash. If the user does not enter "true" or "false"
                // the default value for boolean isFilled will be set to true.
                if (filled.equals("true")) {
                        isFilled = true;   // set isFilled as true if user entered string as "true"
                }
                else if (filled.equals("false")) {
                        isFilled = false;  // set isFilled as false if user entered string as "false"
                }
                else {
                        isFilled = true;   // for default if user does not enter "true" or "false"
                }
                // instantiates geoObjectTriangle object using the appropriate constructor
                GeometricObject geoObjectTriangle = new Triangle(color, isFilled, side1, side2, side3);

                // Code to test Triangle
                System.out.println();
                System.out.println("Triangle:");
                displayGeometricObject(geoObjectTriangle);

                // Code to test Circle
                // instantiates geoObjectCircle object using the appropriate constructor
                // For the purpose of this program the color is set to yellow, filled is true, and radius is 4
                GeometricObject geoObjectCircle = new Circle("yellow", true, 4);
                System.out.println();
                System.out.println("Circle:");
                displayGeometricObject(geoObjectCircle);

                // Code to test Rectangle
                // instantiates geoObjectRectangle object using the appropriate constructor
                // For the purpose of this program the color is red, filled is false, width is 4, and height is 3
                GeometricObject geoObjectRectangle = new Rectangle("red", false, 4, 3);
                System.out.println();
                System.out.println("Rectangle:");
                displayGeometricObject(geoObjectRectangle);
        }       // end main

        /** A method for comparing the areas of two geometric objects */
        public static boolean equalArea(GeometricObject object1, GeometricObject object2) {
                return object1.getArea() == object2.getArea();
        }

        /** A method for displaying a geometric object */
        public static void displayGeometricObject(GeometricObject object) {
                System.out.printf("Area: %.2f%n", object.getArea());
                System.out.printf("Perimeter: %.2f%n", object.getPerimeter());
                System.out.println("Color: " + object.getColor());
                System.out.println("Filled: " + object.isFilled());
        }

        /************************************************************************************
         * public static boolean isValidTriangle(double side1, double side2, double side3)
         * Description: Determine whether the three side lengths make up a valid triangle
         * Input: double side1, double side2, double side3 (these three side lengths must be greater than 0)
         * Return: boolean. True means it's a valid triangle
         *                 False means it is NOT a valid triangle
         *************************************************************************************/
        public static boolean isValidTriangle(double side1, double side2, double side3) {
                if ((side1 + side2 < side3) || (side1 + side3 < side2) ||
                        (side2 + side3 < side1)) {
                        return false;
                }
                else {
                        return true;
                }
        }       // end of isNotValidTriangle
}       //end of class TestGeometricObject

/****************************************************************************************
 * abstract class GeometricObject
 * This class was provided in the book
 * This is super class for Circle, Rectangle, and Triangle
 *****************************************************************************************/
abstract class GeometricObject {
        private String color;
        private boolean filled;
        private java.util.Date dateCreated;

        /************************************************
         * Construct a default geometric object
         * Default color = "white", filled = true, dateCreated = current date
        *************************************************/
        protected GeometricObject() {
                this.color = "white";
                this.filled = true;
                dateCreated = new java.util.Date();
        }

        /* Overloading Constructor a geometric object with color and filled value */
        protected GeometricObject(String color, boolean filled) {
                dateCreated = new java.util.Date();
                this.color = color;
                this.filled = filled;
        }

        /** Return color */
        /* getter (or accessor) */
        public String getColor() {
                return color;
        }

        /** Set a new color */
        /* setter (or mutator) */
        public void setColor(String color) {
                this.color = color;
        }

        /** Return filled. Since filled is boolean, the getter method is named isFilled */
        public boolean isFilled() {
                return filled;
        }

        /** Set a new filled */
        public void setFilled(boolean filled) {
                this.filled = filled;
        }

        /** Get dateCreated */
        public java.util.Date getDateCreated() {
                return dateCreated;
        }
        @Override
        public String toString() {
                return "created on " + dateCreated + "\ncolor: " + color +
                        " and filled: " + filled;
        }

        /** Abstract method getArea */
        public abstract double getArea();

        /** Abstract method getPerimeter */
        public abstract double getPerimeter();
}   // end of GeometricObject class

/*************************************************************************************************/
class Triangle extends GeometricObject {
        // The three side lengths must be greater than 0
        private double side1, side2, side3;

        /************************************************
         * Construct a default triangle object
         * Default side1 = 1, side2 = 1, side3 = 1
         *************************************************/
        public Triangle() {
                this.side1 = 1;
                this.side2 = 1;
                this.side3 = 1;
        }

        /************************************************
         * Construct a triangle object with a given color, filled,
         * side 1, side 2, and side 3. The color and filled will be
         * passed to the GeometricObject superclass.
         *************************************************/
        public Triangle(String color, boolean filled, double side1, double side2, double side3) {
                super(color, filled);
                this.side1 = side1;
                this.side2 = side2;
                this.side3 = side3;
        }

        /** Return side1 **/
        public double getSide1() {
                return side1;
        }

        /** Set a new side1 **/
        public void setSide1(double side1) {
                this.side1 = side1;
        }

        public double getSide2() {
                return side2;
        }

        public void setSide2(double side2) {
                this.side2 = side2;
        }

        public double getSide3() {
                return side3;
        }

        public void setSide3(double side3) {
                this.side3 = side3;
        }

        @Override /** Return area */
        /*****************************************
         * public double getArea()
         * Desciption: Calculate area of the triangle using Heron's formula.
         * Heron's formula was used to calculate the area of a triangle. First, the semi perimeter of
         * the triangle was calculated. Then the semi perimeter was used to calculate the area of the triangle.
         * Input: double side1, double side2, double side3 (all three side lengths must be greater than 0)
         * Returns: area of triangle
         *****************************************/
        public double getArea() {
                double semiPerimeter;

                semiPerimeter = ((side1 + side2 + side3) / 2);   // calculate semi perimeter
                return (Math.sqrt(semiPerimeter * (semiPerimeter - side1) * (semiPerimeter - side2) * (semiPerimeter - side3)));
        }

        /*****************************************
         * public double getPerimeter()
         * Desciption: Calculate perimeter of the triangle
         * Input: double side1, double side2, double side3
         * Returns: perimeter of triangle
         *****************************************/
        @Override /** Return perimeter */
        public double getPerimeter() {
                return (side1 + side2 + side3);
        }

        // This is not used in the main program but it was provided
        @Override /** Return a string representation of a Triangle object */
        public String toString() {
                return (super.toString() + " side 1: " + side1 + " side 2: " + side2 + " side 3: " + side3);
        }
}   // end of Triangle Class

/***************************************************************************************************/
// This code was provided from the book
class Circle extends GeometricObject {
        private double radius;

        /** DEFAULT CONSTRUCTOR **/
        public Circle() {  // default for radius is 1.0
                this.radius = 1.0;
        }

        /** OVERLOAD CONSTRUCTOR WITH RADIUS PARAMETER **/
        public Circle(double radius) {
                this.radius = radius;
        }

        /** OVERLOAD CONSTRUCTOR WITH COLOR, FILLED, AND RADIUS PARAMETERS **/
        public Circle (String color, boolean filled, double radius) {
                super(color, filled);   // calls constructor in the super class
                this.radius = radius;
        }

        /** Return radius */
        public double getRadius() {
                return radius;
        }

        /** Set a new radius */
        public void setRadius(double radius) {
                this.radius = radius;
        }

        @Override /** Return area */
        public double getArea() {
                return (radius * radius * Math.PI);
        }

        /** Return diameter */
        public double getDiameter() {
                return (2 * radius);
        }

        @Override /** Return perimeter */
        public double getPerimeter() {
                return (2 * radius * Math.PI);
        }

        /* Print the circle info */
        // This function was provided but not used
        public void printCircle() {
                System.out.println("The circle is created " + getDateCreated() +
                        " and the radius is " + radius);
        }

        // This function was provided but not used
        @Override /** Return a string representation of a Circle object */
        public String toString() {
                return super.toString() + " radius: " + radius;
        }
}   // end of class Circle

/**************************************************************************************************/
// This code was provided from the book
class Rectangle extends GeometricObject {
        private double width, height;

        /** DEFAULT CONSTRUCTOR **/
        public Rectangle() {
                this.width = 1.0;
                this.height = 1.0;
        }

        /** OVERLOAD CONSTRUCTOR WITH WIDTH AND HEIGHT PARAMETERS **/
        public Rectangle(double width, double height) {
                this.width = width;
                this.height = height;
        }

        /** OVERLOAD CONSTRUCTOR WITH COLOR, FILLED, WIDTH, AND HEIGHT PARAMETERS **/
        public Rectangle(String color, boolean filled, double width, double height) {
                super(color, filled);   // calls constructor in the super class
                this.width = width;
                this.height = height;
        }

        /** Return width */
        public double getWidth() {
                return width;
        }

        /** Set a new width */
        public void setWidth(double width) {
                this.width = width;
        }

        /** Return height */
        public double getHeight() {
                return height;
        }

        /** Set a new height */
        public void setHeight(double height) {
                this.height = height;
        }

        @Override /** Return area */
        public double getArea() {
                return (width * height);
        }

        @Override /** Return perimeter */
        public double getPerimeter() {
                return (2 * (width + height));
        }

        @Override /** Return a string representation of a Rectangle object */
        public String toString() {
                return super.toString() + " width: " + width + " height: " + height;
        }
}   // end of Rectangle class

