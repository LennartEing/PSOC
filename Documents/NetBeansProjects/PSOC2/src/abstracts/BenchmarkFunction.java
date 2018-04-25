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
public abstract class BenchmarkFunction {
    
    public double boundValue;
    
    public BenchmarkFunction(){}
    
    abstract public double distanceToGlobalMinimum(double[] position);
    abstract public double calculate(double[] position);
    
    public double getBoundValue() {
        return this.boundValue;
    }
    
}
