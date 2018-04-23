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
public class SlopeFunction extends DifficultyFunction {
    
    public SlopeFunction(double boundValue) throws IllegalInputException {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        int dimension = 0;
        double tmpVal = 0;
        while(dimension < position.length) {
            tmpVal += (1/(2 * this.boundValue)) * (position[dimension] + boundValue);
            dimension++;
        }
        return tmpVal / dimension;
    }    
}
