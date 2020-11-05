package sample.models;

import java.util.ArrayList;
import java.util.List;

public class Plot {

    private List<Coordinate> coordinates;
    private static double x0;
    private static double X;
    private static double y0;
    private static int N;
    private static int n0;
    private static int n1;

    private static double C;

    public Plot(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Plot() {
        this.coordinates = new ArrayList<>();
        x0 = 1;
        X = 6;
        y0 = 2;
        N = 10;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public static double getX() {
        return X;
    }

    public static void setX(double x) {
        X = x;
    }

    public static double getX0() {
        return x0;
    }

    public static void setX0(double x0) {
        Plot.x0 = x0;
    }

    public static double getY0() {
        return y0;
    }

    public static void setY0(double y0) {
        Plot.y0 = y0;
    }

    public static int getN() {
        return N;
    }

    public static void setN(int n) {
        N = n;
    }

    public static int getN0() {
        return n0;
    }

    public static void setN0(int n0) {
        Plot.n0 = n0;
    }

    public static int getN1() {
        return n1;
    }

    public static void setN1(int n1) {
        Plot.n1 = n1;
    }

    public static double getC() {
        return C;
    }

    public static void setC(double c) {
        C = c;
    }
}
