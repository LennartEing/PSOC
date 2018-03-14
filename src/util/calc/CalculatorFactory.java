/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.calc;

import util.calc.CalculatorConfiguration;
import util.calc.Calculator;
import abstracts.DifficultyFunction;
import abstracts.FitnessFunction;
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
import excep.IllegalInputException;
import excep.MissingFunctionException;

/**
 *
 * @author leing
 * @param <T>
 * @param <K>
 */
public final class CalculatorFactory <T extends FitnessFunction, K extends DifficultyFunction> {
    
    private CalculatorConfiguration config;
    
    public CalculatorFactory(String fitnessFunctionName, String difficultyFunctionName, double boundValue, int timeFactor) {
        configure(fitnessFunctionName, difficultyFunctionName, boundValue, timeFactor);
    }
    
    public void configure(String fitnessFunctionName, String difficultyFunctionName, double boundValue, int timeFactor) {
        this.config = new CalculatorConfiguration(fitnessFunctionName, difficultyFunctionName, boundValue, timeFactor);
    }
    
    public Calculator getCalculator() throws MissingFunctionException {
        T fitnessFunction = null;
        K difficultyFunction = null;
        Calculator calculator = null;
        try {
            //Finding the correct Fitnessfunction
            if(this.config.getFitnessName().equals("ackley")) fitnessFunction = (T)new AckleyFunction(this.config.getBoundValue());
            if(this.config.getFitnessName().equals("griewank")) fitnessFunction = (T)new GriewankFunction(this.config.getBoundValue());
            if(this.config.getFitnessName().equals("rastrigin")) fitnessFunction = (T)new RastriginFunction(this.config.getBoundValue());
            if(this.config.getFitnessName().equals("rosenbrock")) fitnessFunction = (T)new RosenbrockFunction(this.config.getBoundValue());
            if(this.config.getFitnessName().equals("sphere")) fitnessFunction = (T)new SphereFunction(this.config.getBoundValue());
            if(this.config.getFitnessName().equals("styblinskitang")) fitnessFunction = (T)new StyblinskiTangFunction(this.config.getBoundValue());
            //Finding the correct Difficulty
            if(this.config.getDifficultyName().equals("gaussian")) difficultyFunction = (K) new GaussianFunction(this.config.getBoundValue());
            if(this.config.getDifficultyName().equals("parable")) difficultyFunction = (K) new ParableFunction(this.config.getBoundValue());
            if(this.config.getDifficultyName().equals("slope")) difficultyFunction = (K) new SlopeFunction(this.config.getBoundValue());
            if(this.config.getDifficultyName().equals("flat")) difficultyFunction = (K) new FlatFunction(this.config.getBoundValue());
            calculator = new Calculator(fitnessFunction, difficultyFunction, this.config.getTimeFactor());
        } catch (IllegalInputException e) {
            throw new MissingFunctionException();
        }
        return calculator;
    }
    
}
