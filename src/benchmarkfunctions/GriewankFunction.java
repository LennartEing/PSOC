/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmarkfunctions;

import abstracts.FitnessFunction;

/**
 *
 * @author leing
 */
public class GriewankFunction extends FitnessFunction {

    public GriewankFunction(double boundValue) {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position, int dimensions) {
        double firstTerm = 0;
        double secondTerm = 0;
        return 1;
    }
    
}
