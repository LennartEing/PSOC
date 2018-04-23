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

    public StyblinskiTangFunction() {}

    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        double posVal;
        for(int i = 0; i < position.length; i++) {
            posVal = position[i];
            tmpVal += Math.pow(posVal, 4) - 16 * Math.pow(posVal, 2) + 5 * posVal;            
        }
        return tmpVal / 2 + 39.16599 * position.length;
    }

    @Override
    public double distanceToGlobalMinimum(double[] position) {
        double tmp = 0;
        double tmp_val;
        for(int i = 0; i < position.length; i++) {
            tmp_val = position[i] + 2.903534;
            tmp = tmp_val * tmp_val;
        }
        return Math.sqrt(tmp);
    }
    
}
