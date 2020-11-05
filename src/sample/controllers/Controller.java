package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import sample.models.Coordinate;
import sample.models.Plot;
import sample.models.Points;
import sample.services.ErrorService;
import sample.services.GlobalErrorsService;
import sample.services.NumericalMethodsService;

public class Controller {

    NumericalMethodsService numericalMethodsService = new NumericalMethodsService();
    ErrorService errorService = new ErrorService();
    GlobalErrorsService globalErrorsService = new GlobalErrorsService();

    @FXML
    private Tab solutionTab;

    @FXML
    private LineChart<Double, Double> solutionChart;

    @FXML
    private LineChart<Double, Double> LTEChart;

    @FXML
    private LineChart<Double, Double> globalErrorsChart;

    @FXML
    private Tab LTETab;

    @FXML
    private Tab globalErrorsTab;

    @FXML
    private TextField x0Value;

    @FXML
    private TextField XValue;

    @FXML
    private TextField n0Value;

    @FXML
    private TextField n1Value;

    @FXML
    private TextField NValue;

    @FXML
    private TextField YValue;

    @FXML
    private CheckBox exactSwitch;

    @FXML
    private CheckBox eulerSwitch;

    @FXML
    private CheckBox improvedEulerSwitch;

    @FXML
    private CheckBox rungeKuttaSwitch;

    @FXML
    private Button updateButton;

    @FXML
    void initialize(){
        Points points = new Points();
        numericalMethodsService.setDefaultVariable(1, 6, 2, 10 - 1);
        globalErrorsService.setEdgesForGlobalErrors(10, 100);
        numericalMethodsService.computeSolutions(points);
        errorService.computeErrors(points);
        globalErrorsService.computeGlobalErrors(points);

        drawChart(points);
        updateButton.setOnAction(event -> updateInitialValues(points));
    }

    XYChart.Series<Double, Double> createSeries(Plot plot){
        XYChart.Series<Double, Double> sol = new XYChart.Series<>();
        for (Coordinate coordinate : plot.getCoordinates()) {
            sol.getData().add(new XYChart.Data<Double, Double>(coordinate.getX(), coordinate.getY()));
        }
        return sol;
    }

    void drawChart(Points points){
        solutionChart.getData().clear();
        LTEChart.getData().clear();
        globalErrorsChart.getData().clear();

        XYChart.Series<Double, Double> exactSol = createSeries(points.getExactSolution());
        XYChart.Series<Double, Double> eulerSol = createSeries(points.getEulerSolution());
        XYChart.Series<Double, Double> improvedEulerSol = createSeries(points.getImprovedEulerSolution());
        XYChart.Series<Double, Double> rungeKuttaSol = createSeries(points.getRungeKuttaSolution());
        exactSol.setName("Exact solution");
        eulerSol.setName("Euler solution");
        improvedEulerSol.setName("Improved Euler solution");
        rungeKuttaSol.setName("Runge-Kutta solution");

        solutionChart.getData().addAll(exactSol, eulerSol, improvedEulerSol, rungeKuttaSol);

        XYChart.Series<Double, Double> eulerErr = createSeries(points.getEulerError());
        XYChart.Series<Double, Double> improvedEulerErr = createSeries(points.getImprovedEulerError());
        XYChart.Series<Double, Double> rungeKuttaErr = createSeries(points.getRungeKuttaError());
        eulerErr.setName("Euler error");
        improvedEulerErr.setName("Improved Euler error");
        rungeKuttaErr.setName("Runge-Kutta error");

        LTEChart.getData().addAll(eulerErr, improvedEulerErr, rungeKuttaErr);

        XYChart.Series<Double, Double> eulerGlobalErr = createSeries(points.getEulerGlobalError());
        XYChart.Series<Double, Double> improvedEulerGlobalErr = createSeries(points.getImprovedEulerGlobalError());
        XYChart.Series<Double, Double> rungeKuttaGlobalErr = createSeries(points.getRungeKuttaGlobalError());
        eulerGlobalErr.setName("Euler global error");
        improvedEulerGlobalErr.setName("Improved Euler global error");
        rungeKuttaGlobalErr.setName("Runge-Kutta global error");

        globalErrorsChart.getData().addAll(eulerGlobalErr, improvedEulerGlobalErr, rungeKuttaGlobalErr);

        exactSwitch.setOnAction(event -> updateByCheckbox(
                exactSwitch,
                exactSol,
                null,
                null
        ));
        eulerSwitch.setOnAction(event -> updateByCheckbox(
                eulerSwitch,
                eulerSol,
                eulerErr,
                eulerGlobalErr
        ));
        improvedEulerSwitch.setOnAction(event -> updateByCheckbox(
                improvedEulerSwitch,
                improvedEulerSol,
                improvedEulerErr,
                improvedEulerGlobalErr
        ));
        rungeKuttaSwitch.setOnAction(event -> updateByCheckbox(
                rungeKuttaSwitch,
                rungeKuttaSol,
                rungeKuttaErr,
                rungeKuttaGlobalErr
        ));
    }

    void updateByCheckbox(
            CheckBox checkBox,
            XYChart.Series<Double, Double> solSeries,
            XYChart.Series<Double, Double> errSeries,
            XYChart.Series<Double, Double> globalErrSeries
    ){
        if (checkBox.isSelected()) {
            solutionChart.getData().add(solSeries);
            if (errSeries != null){
                LTEChart.getData().add(errSeries);
            }
            if (globalErrSeries != null){
                globalErrorsChart.getData().add(globalErrSeries);
            }
        }
        else {
            solutionChart.getData().remove(solSeries);
            if (errSeries != null){
                LTEChart.getData().remove(errSeries);
            }
            if (globalErrSeries != null){
                globalErrorsChart.getData().remove(globalErrSeries);
            }
        }
    }

    void updateInitialValues(Points points) {
        double x0 = Double.parseDouble(x0Value.getText());
        double X = Double.parseDouble(XValue.getText());
        double y0 = Double.parseDouble(YValue.getText());
        int N = Integer.parseInt(NValue.getText());
        int n0 = Integer.parseInt(n0Value.getText());
        int n1 = Integer.parseInt(n1Value.getText());

        numericalMethodsService.setDefaultVariable(x0, X, y0, N - 1);
        numericalMethodsService.computeSolutions(points);
        errorService.computeErrors(points);

        globalErrorsService.setEdgesForGlobalErrors(n0, n1);
        globalErrorsService.computeGlobalErrors(points);
        drawChart(points);
    }
}