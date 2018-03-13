/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstracts;

import excep.IllegalInputException;

/**
 *
 * @author leing
 */
public abstract class DifficultyFunction {
    
    protected double boundValue;
    
    public DifficultyFunction(double boundValue) throws IllegalInputException {
        try {
            this.setBoundValue(boundValue);
        } catch (IllegalInputException e) {
            throw e;
        }
    }
    
    abstract public double calculate(double position[]);
    public final void setBoundValue(double boundValue) throws IllegalInputException {
        if(boundValue == 0) throw new IllegalInputException("BoundValues cannot be Zero");
        else this.boundValue = Math.abs(boundValue);
    }
    public final double getBoundValue() {
        return this.boundValue;
    }
    
}
