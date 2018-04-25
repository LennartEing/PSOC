/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Observable;
import java.util.Random;
import javax.cache.Cache;

/**
 *
 * @author leing
 */
public class Particle extends Observable implements Runnable {
    
    private final Random randomGen;
    private final long uniqueID;
    private final double precision;
    private final int dimensions;
    private final Calculator calculator;
    private final Cache<Long, double[]> gPositions;
    
    private double[] pPosition;
    private double[] pVelocity;
    private double[] pBestPosition;
    private double pBestValue;
    
    private long gBestUniqueID;
    
    public Particle(long uniqueID, double precision, int dimensions, Calculator calculator, Cache<Long, double[]> gPositions) {
        this.uniqueID = uniqueID;
        this.precision = precision;
        this.dimensions = dimensions;
        this.calculator = calculator;
        this.gPositions = gPositions;
        //This is the randomGenerator used for randomizing particle-movement
        this.randomGen = new Random();   
    }
    
    /*
    This function initializes the random position in the search space of this particle, as well
    as it's velocity
    @param
    @boundValue this is the maximum Value this particles Position will be initialized with on each axis
                from -boundValue to boundValue
    */
    public void initParticlePosition() {
        double boundValue = this.calculator.getBoundValue();
        for(int i = 0; i < this.dimensions; i++) {
            double min = -boundValue;
            double max = boundValue; 
            double randomCoord = min + this.randomGen.nextDouble() * (max - min);
            this.pPosition[i] = randomCoord;
        }
        for(int i = 0; i < dimensions; i++) {
            this.pVelocity[i] =  this.randomGen.nextDouble();
        }
        System.arraycopy(this.pPosition, 0, this.pBestPosition, 0, dimensions);
        this.pBestValue = this.calculator.calculate(this.pPosition);
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
