/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import interfaces.OptimizationValues;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;
import javax.cache.Cache;

/**
 *
 * @author leing
 */
public class Particle extends Observable implements Runnable, OptimizationValues {
    
    private Thread t = null;
    private volatile boolean running= false;
    private final Random randomGen;
    private final long threadId;
    
    private final int dimensions;
    
    private double[] pPosition;
    private double[] pVelocity;
    private double[] pBestPosition;
    private double pBestValue;
    
    private final Calculator calculator;
    
    private final Cache<Long, double[]> gPositions;
    private long gBestThreadId;
    
    public Particle(Cache<Long, double[]> cache, int dimensions, double[][] boundaries) {
        this.dimensions = dimensions;
        this.gPositions = cache;
        this.randomGen = new Random();
        this.threadId = Math.abs(this.randomGen.nextLong());
        this.gBestThreadId = this.threadId;
        this.calculator = new Calculator();
        this.pPosition = new double[dimensions];
        this.pVelocity = new double[dimensions];
        this.pBestPosition = new double[dimensions];
        this.initialize(dimensions, boundaries);
    }
    
    public void setGBestThreadId(long threadId) {
        this.gBestThreadId = threadId;
    }
    
    public long getThreadId() {
        return this.threadId;
    }
    public long getGBestThreadId() {
        return gBestThreadId;
    }
    
    private void move() {
        for(int i = 0; i < this.dimensions; i++) {
            this.pPosition[i] += this.pVelocity[i];
        }
    }
    
    private void reevaluateVelocity() {
        if(this.gBestThreadId != this.threadId) {
            double[] best = this.gPositions.get(this.gBestThreadId);
            for(int i = 0; i < this.dimensions; i++) {
                double firstRandomValue = (2 / standardPSOPsi) * randomGen.nextDouble();
                double secondRandomValue = (2 / standardPSOPsi) * randomGen.nextDouble();
                pVelocity[i] = standardPSOBeta * pVelocity[i]
                        + firstRandomValue * (pBestPosition[i] - pPosition[i])
                        + secondRandomValue * (best[i] - pPosition[i]);
            }
        }
    }
    
    private void reevaluateBests() {
        double tmpBestValue = this.calculator.calculate(this.pPosition, this.dimensions);
        if(tmpBestValue < this.pBestValue) {
            //System.out.println(tmpBestValue + " was BETTER than " + this.pBestValue);
            this.pBestValue = tmpBestValue;
            System.arraycopy(this.pPosition, 0, this.pBestPosition, 0, this.dimensions);
            this.gPositions.put(this.threadId, this.pBestPosition);
        }
        if(tmpBestValue < this.calculator.calculate(this.gPositions.get(this.gBestThreadId), this.dimensions)) {
            setChanged();
            notifyObservers(this.threadId);
            System.out.println(tmpBestValue + " was BETTER than everyone else;");
            System.out.println(Arrays.toString(this.pPosition));
        }
    }
    
    @Override
    public void run() {
        while(running) {
            this.move();
            this.reevaluateVelocity();
            this.reevaluateBests();
        }
    }

    public void start() {
        this.running = true;
        if(this.t == null) {
            this.t = new Thread(this, Long.toString(this.threadId));
            this.t.start();
        } else {
            this.t.start();
        }
    }
    
    public void stop() {
        this.running = false;
    }

    private void initialize(int dimensions, double[][] boundaries) {
        for(int i = 0; i < dimensions; i++) {
            double min = boundaries[i][0];
            double max = boundaries[i][1];
            double randomCoord = min + this.randomGen.nextDouble() * (max - min);
            this.pPosition[i] = randomCoord;
        }
        for(int i = 0; i < dimensions; i++) {
            this.pVelocity[i] =  this.randomGen.nextDouble();
        }
        System.arraycopy(this.pPosition, 0, this.pBestPosition, 0, dimensions);
        this.pBestValue = this.calculator.calculate(this.pPosition, this.dimensions);
        this.gPositions.put(this.threadId, this.pBestPosition);
        setChanged();
        notifyObservers(this.threadId);
    }
}
