/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import interfaces.FitnessFunction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author leing
 */
public class Calculator implements FitnessFunction {
    
    public Calculator() {}

    @Override
    public double calculate(double[] position, int dimensions) {
        double tmpVal = 0;
        for(int i = 0; i < dimensions; i++) {
            double tmpPositionValue = position[i];
            tmpVal += Math.pow(tmpPositionValue, 2) - 10 * Math.cos(2 * Math.PI * tmpPositionValue) + 10;
        }
        return tmpVal;
    } 
}
