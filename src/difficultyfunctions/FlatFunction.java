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
public class FlatFunction extends DifficultyFunction {

    public FlatFunction(double boundValue) throws IllegalInputException {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        return 0.0;
    }
    
}
