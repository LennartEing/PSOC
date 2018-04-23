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

    public RastriginFunction() {}
    
    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        for(int i = 0; i < position.length; i++) {
            double tmpPosValue = position[i];
            tmpVal += Math.pow(tmpPosValue, 2) - 10 * Math.cos(2 * Math.PI * tmpPosValue) + 10;
        }
        return tmpVal;
    }

    @Override
    public double distanceToGlobalMinimum(double[] position) {
        double tmp = 0;
        double tmp_val;
        for(int i = 0; i < position.length; i++) {
            tmp_val = position[i];
            tmp = tmp_val * tmp_val;
        }
        return Math.sqrt(tmp);
    }
}
