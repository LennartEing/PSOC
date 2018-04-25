/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import abstracts.BenchmarkFunction;
import abstracts.DifficultyFunction;
import benchmarkfunctions.AckleyFunction;
import benchmarkfunctions.GriewankFunction;
import benchmarkfunctions.RastriginFunction;
import benchmarkfunctions.RosenbrockFunction;
import benchmarkfunctions.SphereFunction;
import benchmarkfunctions.StyblinskiTangFunction;
import difficultyfunctions.FlatFunction;
import difficultyfunctions.GaussianFunction;
import difficultyfunctions.ParableFunction;
import difficultyfunctions.SlopeFunction;

/**
 *
 * @author leing
 */
public class Calculator <T extends BenchmarkFunction, K extends DifficultyFunction> {
    
    private final T benchmarkFunction;
    private final K difficultyFunction;
    private final int timeFactor;
   
    /**
     * This constructor is used for CalculatorOrigins, and for this purpose only.
    To create a Calculator, use thiss function, and then call getCalculator() on
    it instead.
    * @param
     */
    public Calculator(){
        this.benchmarkFunction = null;
        this.difficultyFunction = null;
        this.timeFactor = 0;
    }
    
    private Calculator(T benchmarkFunction, K difficultyFunction, int timeStretchFactor) {
        this.benchmarkFunction = benchmarkFunction;
        this.difficultyFunction = difficultyFunction;
        this.timeFactor = timeStretchFactor;
    }
    
    /*
    This function returns a new Calculator with given parameters used.
    @param
    @benchmarkFunctionName This parameter specifies the benchmarkfunction to be
    used by its name
    @difficultyFunctionName See "benchmarkFunctionName"
    @timeStretchFactor This parameter specifies a factor, by which this calculators
    operations a distorted (into length)
    */
    public Calculator getCalculator(String benchmarkFunctionName, String difficultyFunctionName, int timeStretchFactor) {
        T benchmarkFunction_tmp = null;
        K difficultyFunction_tmp = null;
        double boundValue;
        if("ackley".equals(benchmarkFunctionName)) benchmarkFunction_tmp = (T) new AckleyFunction();
        if("griewank".equals(benchmarkFunctionName)) benchmarkFunction_tmp = (T) new GriewankFunction();
        if("rastrigin".equals(benchmarkFunctionName)) benchmarkFunction_tmp = (T) new RastriginFunction();
        if("rosenbrock".equals(benchmarkFunctionName)) benchmarkFunction_tmp = (T) new RosenbrockFunction();
        if("sphere".equals(benchmarkFunctionName)) benchmarkFunction_tmp = (T) new SphereFunction();
        if("styblinskiTang".equals(benchmarkFunctionName)) benchmarkFunction_tmp = (T) new StyblinskiTangFunction();
        boundValue = benchmarkFunction_tmp.getBoundValue();
        if("flat".equals(difficultyFunctionName)) difficultyFunction_tmp = (K) new FlatFunction(boundValue);
        if("gaussian".equals(difficultyFunctionName)) difficultyFunction_tmp = (K) new GaussianFunction(boundValue);
        if("parable".equals(difficultyFunctionName)) difficultyFunction_tmp = (K) new ParableFunction(boundValue);
        if("slope".equals(difficultyFunctionName)) difficultyFunction_tmp = (K) new SlopeFunction(boundValue);
        return new Calculator(benchmarkFunction_tmp, difficultyFunction_tmp, timeStretchFactor);
    }
   
    /*
    Calculates the positional value at a given position accodring to which BenchmarkFunction was given
    @param
    @position The position the positional value should be calculated at
    */
    public double calculate(double[] position) {
        double positionalDifficulty = this.difficultyFunction.calculate(position);
        double result;
        int i = 0;
        do {
            result = this.benchmarkFunction.calculate(position);
            i++;
        } while(i < positionalDifficulty * this.timeFactor);
        return result;
    }
    
    /*
    Returns the distance to the benchmarkfunctions global minimum
    @param
    @position The position from which the distance is calculated
    */
    public double distanceToGlobalMinimum(double[] position) {
        return this.benchmarkFunction.distanceToGlobalMinimum(position);
    }

    double getBoundValue() {
        return this.benchmarkFunction.getBoundValue();
    }
}
