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
    
    protected double boundValue;
    int dimensions;
    
    public FitnessFunction(double boundValue) {    
        this.setBoundValue(boundValue);
    }
    
    abstract public double calculate(double[] position);
    public final void setBoundValue(double boundValue) {
        this.boundValue = Math.abs(boundValue);
    }
    public final double getBoundValue() {
        return this.boundValue;
    }
    
}
