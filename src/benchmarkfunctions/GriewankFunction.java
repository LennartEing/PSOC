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
    public double calculate(double[] position) {
        double firstTerm = 0;
        double secondTerm = 1;
        double tmpPosValue;
        for(int i = 0; i < position.length; i++) {
            tmpPosValue = position[i];
            firstTerm += Math.pow(tmpPosValue, 2);
            secondTerm *= Math.cos(tmpPosValue/Math.sqrt(i+1));
        }
        firstTerm = (1/4000.) * firstTerm;
        secondTerm = -secondTerm;
        firstTerm = firstTerm + secondTerm + 1;
        return firstTerm;
    }
    
}
