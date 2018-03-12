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
public class RosenbrockFunction extends FitnessFunction {

    public RosenbrockFunction(double boundValue) {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position, int dimensions) {
        double tmpVal = 0;
        for(int i= 0; i < dimensions - 1; i++) {
            double tmpPosValue = position[i];
            tmpVal += 100 * Math.pow(position[i + 1] - Math.pow(tmpPosValue, 2), 2)
                    + Math.pow(tmpPosValue - 1, 2);
        }
        return tmpVal;
    }
    
}
