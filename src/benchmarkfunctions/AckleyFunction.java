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
public class AckleyFunction extends FitnessFunction {

    public AckleyFunction(double boundValue) {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position, int dimensions) {
        return 1;
    }
    
}
