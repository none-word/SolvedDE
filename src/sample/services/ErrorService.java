package sample.services;

import sample.models.Coordinate;
import sample.models.Plot;
import sample.models.Points;

import java.util.ArrayList;

public class ErrorService {

    NumericalMethodsService numericalMethodsService = new NumericalMethodsService();

    public void computeErrors(Points points){
        points.setEulerError(this.eulerErrors(Plot.getN()));
        points.setImprovedEulerError(this.improvedEulerErrors(Plot.getN()));
        points.setRungeKuttaError(this.rungeKuttaErrors(Plot.getN()));
    }

    public Plot eulerErrors(double steps){
        ArrayList<Coordinate> resultErrors = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / steps;

        double prevX = left;
        double prevY = 0;
        resultErrors.add(
                new Coordinate(prevX, prevY)
        );
        for (int i = 1; i <= steps; i++) {
            double x = i * h + left;
            resultErrors.add(
                    new Coordinate(x, eulerError(prevX, h))
            );
            prevX = x;
        }

        return new Plot(resultErrors);
    }

    public Plot improvedEulerErrors(double steps){
        ArrayList<Coordinate> resultErrors = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / steps;

        double prevX = left;
        double prevY = 0;
        resultErrors.add(
                new Coordinate(prevX, prevY)
        );
        for (int i = 1; i <= steps; i++) {
            double x = i * h + left;
            resultErrors.add(
                    new Coordinate(x, improvedEulerError(prevX, h))
            );
            prevX = x;
        }

        return new Plot(resultErrors);
    }

    public Plot rungeKuttaErrors(double steps){
        ArrayList<Coordinate> resultErrors = new ArrayList<>();

        double left = Plot.getX0();
        double right = Plot.getX();

        double h = (right - left) / steps;

        double prevX = left;
        double prevY = 0;
        resultErrors.add(
                new Coordinate(prevX, prevY)
        );
        for (int i = 1; i <= steps; i++) {
            double x = i * h + left;
            resultErrors.add(
                    new Coordinate(x, rungeKuttaError(prevX, h))
            );
            prevX = x;
        }

        return new Plot(resultErrors);
    }

    private double eulerError(double x, double h){
        double exactNext = numericalMethodsService.exactFunction(x + h);
        double exactCurrent = numericalMethodsService.exactFunction(x);
        return exactNext - exactCurrent - h * numericalMethodsService.f(x, exactCurrent);
    }

    private double improvedEulerError(double x, double h){
        double y = numericalMethodsService.exactFunction(x);
        double K_1_i = numericalMethodsService.f(x, y);
        double K_2_i = numericalMethodsService.f(x + h, y + h * K_1_i);
        double nextY = numericalMethodsService.exactFunction(x + h);

        return nextY - y - (h / 2) * (K_1_i + K_2_i);
    }

    private double rungeKuttaError(double x, double h){
        double y = numericalMethodsService.exactFunction(x);
        double k_1_i = numericalMethodsService.f(x, y);
        double k_2_i = numericalMethodsService.f(x + h / 2, y + (h / 2) * k_1_i);
        double k_3_i = numericalMethodsService.f(x + h / 2, y + (h / 2) * k_2_i);
        double k_4_i = numericalMethodsService.f(x + h, y + h * k_3_i);
        double nextY = numericalMethodsService.exactFunction(x + h);

        return nextY - y - (h / 6) * (k_1_i + 2 * k_2_i + 2 * k_3_i + k_4_i);
    }
}
