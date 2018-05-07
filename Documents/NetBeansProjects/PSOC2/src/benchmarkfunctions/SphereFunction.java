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
public class SphereFunction extends BenchmarkFunction {

    public SphereFunction() {
        this.boundValue = 1000;
    }

    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        for(int i = 0; i < position.length; i++) {
            tmpVal += Math.pow(position[i], 2);
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
