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
public class IllegalInputException extends Exception {

    private final String exceptionMessage;
    
    public IllegalInputException(String message) {
        super(message);
        this.exceptionMessage = message;
    }
    
    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
    
}
