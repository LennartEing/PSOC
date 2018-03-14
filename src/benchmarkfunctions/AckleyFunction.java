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
        double firstTerm = 0;
        double secondTerm = 0;
        double tmpPosValue;
        for(int i = 0; i < position.length; i++) {
            tmpPosValue = position[i];
            firstTerm += Math.pow(tmpPosValue, 2);
            secondTerm += Math.cos(standardAckleyC * tmpPosValue);
        }
        firstTerm = (1/(double)position.length) * firstTerm;
        firstTerm = Math.sqrt(firstTerm);
        firstTerm = -standardAckleyB * firstTerm;
        firstTerm = -standardAckleyA * Math.exp(firstTerm);
        secondTerm = (1/position.length) * secondTerm;
        secondTerm = -Math.exp(secondTerm);
        firstTerm = firstTerm + secondTerm + standardAckleyA + 1;
        return firstTerm;
    }
    
}
