/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmarkfunctions;

import abstracts.FitnessFunction;
import interfaces.OptimizationValues;

/**
 *
 * @author leing
 */
public class AckleyFunction extends FitnessFunction implements OptimizationValues {

    public AckleyFunction(double boundValue) {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        double tmpValOne = 0;
        double tmpValTwo = 0;
        for(int i = 0; i < position.length; i++) {
            tmpValOne += Math.pow(position[i], 2);
            tmpValTwo += Math.cos(standardAckleyC * position[i]);
        }
        tmpValOne = -standardAckleyB * Math.sqrt((1/position.length) * tmpValOne);
        tmpValTwo = (1/position.length) * tmpValTwo;
        tmpValOne = -standardAckleyA * Math.exp(tmpValOne);
        tmpValTwo = -Math.exp(tmpValTwo);
        return tmpValOne + tmpValTwo + standardAckleyA + Math.E;
    }
    
}
