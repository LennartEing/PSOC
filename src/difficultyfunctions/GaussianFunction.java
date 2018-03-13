/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package difficultyfunctions;

import abstracts.DifficultyFunction;
import excep.IllegalInputException;

/**
 *
 * @author leing
 */
public class GaussianFunction extends DifficultyFunction {

    public GaussianFunction(double boundValue) throws IllegalInputException {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        double tmpVal = 0;
        double stretchFactor = 2 * Math.pow(boundValue, 2);
        for(int i = 0; i < position.length; i++)  {
            tmpVal += Math.pow(position[i], 2) / stretchFactor;
        }
        return Math.exp(-tmpVal);
    }
}
