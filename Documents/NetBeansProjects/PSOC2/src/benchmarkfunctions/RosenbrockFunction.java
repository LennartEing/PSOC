/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmarkfunctions;

import abstracts.BenchmarkFunction;

/**
 *
 * @author leing
 */
public class RosenbrockFunction extends BenchmarkFunction {

    public RosenbrockFunction() {
        this.boundValue = 2.048;
    }
    
    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        for(int i= 0; i < position.length - 1; i++) {
            double tmpPosValue = position[i];
            tmpVal += 100 * Math.pow(position[i + 1] - Math.pow(tmpPosValue, 2), 2)
                    + Math.pow(tmpPosValue - 1, 2);
        }
        return tmpVal;
    }

    @Override
    public double distanceToGlobalMinimum(double[] position) {
        double tmp = 0;
        double tmp_val;
        for(int i = 0; i < position.length; i++) {
            tmp_val = position[i] - 1.;
            tmp = tmp_val * tmp_val;
        }
        return Math.sqrt(tmp);
        
    }
    
}
