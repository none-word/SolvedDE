package sample.models;

public class Points {

    private Plot exactSolution;
    private Plot eulerSolution;
    private Plot improvedEulerSolution;
    private Plot rungeKuttaSolution;
    private Plot eulerError;
    private Plot improvedEulerError;
    private Plot rungeKuttaError;
    private Plot eulerGlobalError;
    private Plot improvedEulerGlobalError;
    private Plot rungeKuttaGlobalError;

    public Points(
            Plot exactSolution,
            Plot eulerSolution,
            Plot improvedEulerSolution,
            Plot rungeKuttaSolution,
            Plot eulerError,
            Plot improvedEulerError,
            Plot rungeKuttaError,
            Plot eulerGlobalError,
            Plot improvedEulerGlobalError,
            Plot rungeKuttaGlobalError
    ) {
        this.exactSolution = exactSolution;
        this.eulerSolution = eulerSolution;
        this.improvedEulerSolution = improvedEulerSolution;
        this.rungeKuttaSolution = rungeKuttaSolution;
        this.eulerError = eulerError;
        this.improvedEulerError = improvedEulerError;
        this.rungeKuttaError = rungeKuttaError;
        this.eulerGlobalError = eulerGlobalError;
        this.improvedEulerGlobalError = improvedEulerGlobalError;
        this.rungeKuttaGlobalError = rungeKuttaGlobalError;
    }

    public Points() {
        this.exactSolution = new Plot();
        this.eulerSolution = new Plot();
        this.improvedEulerSolution = new Plot();
        this.rungeKuttaSolution = new Plot();
        this.eulerError = new Plot();
        this.improvedEulerError = new Plot();
        this.rungeKuttaError = new Plot();
        this.eulerGlobalError = new Plot();
        this.improvedEulerGlobalError = new Plot();
        this.rungeKuttaGlobalError = new Plot();
    }

    public Plot getExactSolution() {
        return exactSolution;
    }

    public void setExactSolution(Plot exactSolution) {
        this.exactSolution = exactSolution;
    }

    public Plot getEulerSolution() {
        return eulerSolution;
    }

    public void setEulerSolution(Plot eulerSolution) {
        this.eulerSolution = eulerSolution;
    }

    public Plot getImprovedEulerSolution() {
        return improvedEulerSolution;
    }

    public void setImprovedEulerSolution(Plot improvedEulerSolution) {
        this.improvedEulerSolution = improvedEulerSolution;
    }

    public Plot getRungeKuttaSolution() {
        return rungeKuttaSolution;
    }

    public void setRungeKuttaSolution(Plot rungeKuttaSolution) {
        this.rungeKuttaSolution = rungeKuttaSolution;
    }

    public Plot getEulerError() {
        return eulerError;
    }

    public void setEulerError(Plot eulerError) {
        this.eulerError = eulerError;
    }

    public Plot getImprovedEulerError() {
        return improvedEulerError;
    }

    public void setImprovedEulerError(Plot improvedEulerError) {
        this.improvedEulerError = improvedEulerError;
    }

    public Plot getRungeKuttaError() {
        return rungeKuttaError;
    }

    public void setRungeKuttaError(Plot rungeKuttaError) {
        this.rungeKuttaError = rungeKuttaError;
    }

    public Plot getEulerGlobalError() {
        return eulerGlobalError;
    }

    public void setEulerGlobalError(Plot eulerGlobalError) {
        this.eulerGlobalError = eulerGlobalError;
    }

    public Plot getImprovedEulerGlobalError() {
        return improvedEulerGlobalError;
    }

    public void setImprovedEulerGlobalError(Plot improvedEulerGlobalError) {
        this.improvedEulerGlobalError = improvedEulerGlobalError;
    }

    public Plot getRungeKuttaGlobalError() {
        return rungeKuttaGlobalError;
    }

    public void setRungeKuttaGlobalError(Plot rungeKuttaGlobalError) {
        this.rungeKuttaGlobalError = rungeKuttaGlobalError;
    }
}
