/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excep;

/**
 *
 * @author leing
 */
public class MissingFunctionException extends Exception {
    
    private final static String errorMessage = "You tried using a not existing function";
    
    public MissingFunctionException() {
        super(MissingFunctionException.errorMessage);
    }
    
}
