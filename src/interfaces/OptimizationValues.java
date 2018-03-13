/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author leing
 * */
public interface OptimizationValues {
    
    /*
    Values taken from this link: https://link.springer.com/referenceworkentry/10.1007%2F978-0-387-30164-8_630
    and this Paper: http://ieeexplore.ieee.org/document/985692/#full-text-section
    */
    
    double standardPSOPsi = 2.9922;
    double standardPSOBeta = 0.7298;
    
    /*Values taken from this link: https://www.sfu.ca/~ssurjano/ackley.html */
    
    double standardAckleyA = 20;
    double standardAckleyB = 0.2;
    double standardAckleyC = 2 * Math.PI;
    
}
