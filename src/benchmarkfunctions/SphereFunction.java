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
public class SphereFunction extends FitnessFunction {

    public SphereFunction(double boundValue) {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        for(int i = 0; i < position.length; i++) {
            tmpVal += Math.pow(position[i], 2);
        }
        return tmpVal;
    }
    
}
