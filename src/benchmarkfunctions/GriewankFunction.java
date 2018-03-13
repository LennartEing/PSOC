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
        for(int i = 0; i < position.length; i++) {
            firstTerm += Math.pow(position[i], 2);
            secondTerm *= Math.cos(position[i]/Math.sqrt(i));
        }
        firstTerm = (1/4000) * firstTerm;
        secondTerm = -secondTerm;
        return firstTerm + secondTerm + 1;
    }
    
}
