/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import abstracts.DifficultyFunction;
import abstracts.FitnessFunction;
/**
 *
 * @author leing
 * @param <T>
 * @param <K>
 */
public class Calculator <T extends FitnessFunction, K extends DifficultyFunction>{

    private T fitnessFunction;
    private K difficultyFunction;
    private int timeFactor = 100;
    
    public final void setFitnessFunction(T fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }
    public final T getFitnessFunction() {
        return this.fitnessFunction;
    }
    
    public final void setDifficultyFunction(K difficultyFunction) {
        this.difficultyFunction = difficultyFunction;
    }
    public final K getDifficultyFunction() {
        return this.difficultyFunction;
    }
    
    public final void setTimeFactor(int timeFactor) {
        this.timeFactor = timeFactor;
    }
    public final int getTimeFactor() {
        return this.timeFactor;
    }
    
    public Calculator(T fitnessFunction, K difficultyFunction, int timeFactor) {
        setFitnessFunction(fitnessFunction);
        setDifficultyFunction(difficultyFunction);
        setTimeFactor(timeFactor);
    }
    
    public double calculate(double[] position) {
        double positionalDifficulty = this.difficultyFunction.calculate(position);
        double result;
        int i = 0;
        do {
            result = this.fitnessFunction.calculate(position);
            i++;
        } while(i < positionalDifficulty * this.timeFactor);
        return result;
    } 
}
