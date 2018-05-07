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
    
    /**
     * Creates a BenchmarkFunction
     */
    public BenchmarkFunction(){}
    
    /**
     * Calculates the distance to ths specifics functions global minimum.
     * @param position the position from which the distance is to be calculated
     * @return the distance over euklidean space.
     */
    abstract public double distanceToGlobalMinimum(double[] position);
    
    /**
     * Calculates the value defined by the spcific functions name.
     * @param position
     * @return 
     */
    abstract public double calculate(double[] position);
    
    /**
     * 
     * @return Typically these benchmarkFunctions are calculated in a very specific range in the literature.
     * This value returns these boundaries.
     */
    public double getBoundValue() {
        return this.boundValue;
    }
    
}
