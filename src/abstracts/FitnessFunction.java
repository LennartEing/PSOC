/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstracts;

/**
 *
 * @author leing
 */
public abstract class FitnessFunction {
    
    public FitnessFunction(){}
    
    abstract public double distanceToGlobalMinimum(double[] position);
    abstract public double calculate(double[] position);
    
}
