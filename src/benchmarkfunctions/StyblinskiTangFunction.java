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
public class StyblinskiTangFunction extends FitnessFunction {

    public StyblinskiTangFunction(double boundValue) {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        double posVal;
        for(int i = 0; i < position.length; i++) {
            posVal = position[i];
            tmpVal += Math.pow(posVal, 4) - 16 * Math.pow(posVal, 2) + 5 * posVal;            
        }
        return tmpVal / 2;
    }
    
}
