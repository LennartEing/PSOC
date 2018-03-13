/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author leing
 */
public class CalculatorConfiguration {
    
    private static final String[] fitnessNames = {"ackley", "griewank", "rastrigin",
    "rosenbrock", "sphere", "styblinskitang"};
    private static final String[] difficultyNames = {"gaussian", "parable", "slope", "flat"};
    
    private final String fitnessFunctionName;
    private final String difficultyFunctionName;
    private final double boundValue;
    private final int timeFactor;
    
    public CalculatorConfiguration(String fitnessFunctionName, String difficultyFunctionName, double boundValue, int timeFactor) {
       this.fitnessFunctionName = fitnessFunctionName;
       this.difficultyFunctionName = difficultyFunctionName;
       this.boundValue = boundValue;
       this.timeFactor = timeFactor;
    }
    
    public String[] getFitnessNamesList() {
        return CalculatorConfiguration.fitnessNames;
    } 
    public String[] getDifficultyNamesList() {
        return CalculatorConfiguration.difficultyNames;
    }
    
    public String getFitnessName() {
        return this.fitnessFunctionName;
    }
    public String getDifficultyName() {
        return this.difficultyFunctionName;
    }
    public double getBoundValue() {
        return this.boundValue;
    }
    public int getTimeFactor() {
        return this.timeFactor;
    }
}
