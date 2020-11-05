package sample.services;

import sample.models.Coordinate;
import sample.models.Plot;
import sample.models.Points;

import java.util.ArrayList;

public class NumericalMethodsService {

    public void setDefaultVariable(double x0, double X, double y0, int N){
        Plot.setX0(x0);
        Plot.setX(X);
        Plot.setY0(y0);
        Plot.setN(N);
        Plot.setC(C());
    }

    public void computeSolutions(Points points) {
        this.exactSolution(points.getExactSolution());
        this.eulerMethod(points.getEulerSolution());
        this.improvedEulerMethod(points.getImprovedEulerSolution());
        this.rungeKuttaMethod(points.getRungeKuttaSolution());
    }

    private void exactSolution(Plot plot) {
        ArrayList<Coordinate> resultCoordinates = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / Plot.getN();

        for (int i = 0; i <= Plot.getN(); i++) {
            double x = i * h + left;
            resultCoordinates.add(
                    new Coordinate(x, exactFunction(x))
            );
        }

        plot.setCoordinates(resultCoordinates);
    }

    private void eulerMethod(Plot plot) {
        ArrayList<Coordinate> resultCoordinates = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / Plot.getN();

        double prevX = left;
        double prevY = Plot.getY0();
        resultCoordinates.add(
                new Coordinate(prevX, prevY)
        );
        for (int i = 1; i <= Plot.getN(); i++) {
            double x = i * h + left;
            resultCoordinates.add(
                    new Coordinate(x, eulerFunction(prevX, prevY, h))
            );
            prevX = x;
            prevY = resultCoordinates.get(resultCoordinates.size() - 1).getY();
        }

        plot.setCoordinates(resultCoordinates);

    }

    private void improvedEulerMethod(Plot plot) {
        ArrayList<Coordinate> resultCoordinates = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / Plot.getN();

        double prevX = Plot.getX0();
        double prevY = Plot.getY0();
        resultCoordinates.add(
                new Coordinate(prevX, prevY)
        );
        for (int i = 1; i <= Plot.getN(); i++) {
            double x = i * h + left;
            resultCoordinates.add(
                    new Coordinate(x, improvedEulerFunction(prevX, prevY, h))
            );
            prevX = x;
            prevY = resultCoordinates.get(resultCoordinates.size() - 1).getY();
        }

        plot.setCoordinates(resultCoordinates);

    }

    public void rungeKuttaMethod(Plot plot) {
        ArrayList<Coordinate> resultCoordinates = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / Plot.getN();

        double prevX = Plot.getX0();
        double prevY = Plot.getY0();
        resultCoordinates.add(
                new Coordinate(prevX, prevY)
        );
        for (int i = 1; i <= Plot.getN(); i++) {
            double x = i * h + left;
            resultCoordinates.add(
                    new Coordinate(x, rungeKuttaFunction(prevX, prevY, h))
            );
            prevX = x;
            prevY = resultCoordinates.get(resultCoordinates.size() - 1).getY();
        }

        plot.setCoordinates(resultCoordinates);
    }

    public double f(double x, double y) {
        return 3*y-x*Math.pow(y, 1.0/3);
    }

    public double exactFunction(double x){
        return Math.pow(x / 3 + 1.0 / 6 + Math.exp(2 * x) * Plot.getC(), 3.0 / 2);
    }

    private double eulerFunction(double prevX, double prevY, double h){
        return prevY + h * f(prevX, prevY);
    }

    private double improvedEulerFunction(double prevX, double prevY, double h){
        return prevY + (h / 2) * (f(prevX, prevY) + f(prevX + h, prevY + h * f(prevX, prevY)));
    }

    private double rungeKuttaFunction(double prevX, double prevY, double h){
        double k_1_i = f(prevX, prevY);
        double k_2_i = f(prevX + h / 2, prevY + (h / 2) * k_1_i);
        double k_3_i = f(prevX + h / 2, prevY + (h / 2) * k_2_i);
        double k_4_i = f(prevX + h, prevY + h * k_3_i);

        return prevY + (h / 6) * (k_1_i + 2 * k_2_i + 2 * k_3_i + k_4_i);
    }

    private double C(){
        return (Math.pow(Plot.getY0(), 2.0 / 3) - Plot.getX0() / 3 - 1.0 / 6) / Math.exp(2 * Plot.getX0());
    }
}
