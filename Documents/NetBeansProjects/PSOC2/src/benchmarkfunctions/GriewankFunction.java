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
public class GriewankFunction extends BenchmarkFunction {

    public GriewankFunction() {
        this.boundValue = 600;
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
