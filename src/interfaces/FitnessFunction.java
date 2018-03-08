/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author leing
 */
public interface FitnessFunction {
    
    double calculate(double[] position, int dimensions);
    
}
