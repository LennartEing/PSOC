/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstracts;

import java.util.ArrayList;

/**
 *
 * @author leing
 */
public abstract class FitnessFunction {
    
    double boundValue;
    
    public FitnessFunction(double boundValue) {    
        this.setBoundValue(boundValue);
    }
    
    abstract public double calculate(double[] position, int dimensions);
    public final void setBoundValue(double boundValue) {
        this.boundValue = boundValue;
    }
    public final double getBoundValue() {
        return this.boundValue;
    }
}
