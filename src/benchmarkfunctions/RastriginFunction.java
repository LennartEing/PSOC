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
public class RastriginFunction extends FitnessFunction {

    public RastriginFunction(double boundValue) {
        super(boundValue);
    }
    
    @Override
    public double calculate(double[] position, int dimensions) {
        double tmpVal = 0;
        for(int i = 0; i < dimensions; i++) {
            double tmpPosValue = position[i];
            tmpVal += Math.pow(tmpPosValue, 2) - 10 * Math.cos(2 * Math.PI * tmpPosValue) + 10;
        }
        return tmpVal;
    }
}
