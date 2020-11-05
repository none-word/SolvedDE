package sample.services;

import sample.models.Coordinate;
import sample.models.Plot;
import sample.models.Points;

import java.util.ArrayList;
import java.util.List;

public class GlobalErrorsService {

    ErrorService errorService = new ErrorService();

    public void computeGlobalErrors(Points points){
        this.eulerGlobalErrors(points.getEulerGlobalError());
        this.improvedEulerGlobalErrors(points.getImprovedEulerGlobalError());
        this.rungeKuttaGlobalErrors(points.getRungeKuttaGlobalError());
    }

    public void setEdgesForGlobalErrors(int n0, int n1){
        Plot.setN0(n0);
        Plot.setN1(n1);
    }

    public void eulerGlobalErrors(Plot plot){
        ArrayList<Coordinate> resultGlobalErrors = new ArrayList<>();

        for (int i = Plot.getN0(); i <= Plot.getN1(); i++){
            resultGlobalErrors.add(
                    new Coordinate(
                            i,
                            findMaximum(errorService.eulerErrors(i).getCoordinates())
                    )
            );
        }

        plot.setCoordinates(resultGlobalErrors);
    }

    public void improvedEulerGlobalErrors(Plot plot){
        ArrayList<Coordinate> resultGlobalErrors = new ArrayList<>();

        for (int i = Plot.getN0(); i <= Plot.getN1(); i++){
            resultGlobalErrors.add(
                    new Coordinate(
                            i,
                            findMaximum(errorService.improvedEulerErrors(i).getCoordinates())
                    )
            );
        }

        plot.setCoordinates(resultGlobalErrors);
    }

    public void rungeKuttaGlobalErrors(Plot plot){
        ArrayList<Coordinate> resultGlobalErrors = new ArrayList<>();

        for (int i = Plot.getN0(); i <= Plot.getN1(); i++){
            resultGlobalErrors.add(
                    new Coordinate(
                            i,
                            findMaximum(errorService.rungeKuttaErrors(i).getCoordinates())
                    )
            );
        }

        plot.setCoordinates(resultGlobalErrors);
    }

    private double findMaximum(List<Coordinate> globalErrors){
        double maxError = 0;

        for (Coordinate error :
                globalErrors) {
            maxError = Math.max(error.getY(), maxError);
        }

        return maxError;
    }
}
