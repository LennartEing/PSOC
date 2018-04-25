/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoc2;

import util.Calculator;

/**
 *
 * @author leing
 */
public class PSOC2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc = calc.getCalculator("rastrigin", "flat", 100);
    }
    
}
