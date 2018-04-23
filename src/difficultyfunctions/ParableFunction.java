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
public class ParableFunction extends DifficultyFunction {

    private double stretchingFactor;
    private boolean stretchIsSet = false;
    
    public ParableFunction(double boundValue) throws IllegalInputException {
        super(boundValue);
    }

    @Override
    public double calculate(double[] position) {
        if(!stretchIsSet) this.calculateStretchingFactor(position.length);
        double tmpVal = 0;
        for(int i = 0; i < position.length; i++) {
            tmpVal += Math.pow(position[i] / this.stretchingFactor, 2);
        }
        return -tmpVal + 1;
    }
    
    private void calculateStretchingFactor(int dimensions) {
        double tmpVal = 0;
        for(int i = 0; i < dimensions; i++) {
            tmpVal += Math.pow(this.boundValue, 2);
        }
        this.stretchingFactor = Math.sqrt(tmpVal);
        this.stretchIsSet = true;
    }
    
}
