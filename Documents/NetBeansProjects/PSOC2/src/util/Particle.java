/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static interfaces.OptimizationValues.standardPSOBeta;
import static interfaces.OptimizationValues.standardPSOPsi;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;
import javax.cache.Cache;

/**
 *
 * @author leing
 */
class Particle extends Observable implements Runnable {
    
    public boolean isRunning = false;
    
    public long gBestUniqueId;
    private double systemPrecision;
    private long uniqueID;
    private int systemDimensions;
    private Calculator calculator;
    private Cache<Long, double[]> gPositions;
    private double[] pPosition;
    private final Random randGen;
    private double[] pVelocity;
    private double pBestValue;
    private double[] pBestPosition;
    
    public Particle() {
        this.randGen = new Random();
    }

    @Override
    public void run() {
        this.reevaluateBests();
        this.reevaluateVelocity();
        this.move();
        this.isRunning = false;
    }

    void setUniqueID(long particleID) {
        this.uniqueID = particleID;
    }

    void setSystemPrecision(double systemPrecision) {
        this.systemPrecision = systemPrecision;
    }

    void setSystemDimensions(int systemDimensions) {
        this.systemDimensions = systemDimensions;
    }

    void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    void setGPositions(Cache<Long, double[]> gPositions) {
        this.gPositions = gPositions;
    }

    void initPosition() {
        pPosition = new double[systemDimensions];
        for(int i = 0; i < systemDimensions; i++) {
            double max = calculator.getBoundValue();
            double min = -max;
            double randomCoordinate = min + this.randGen.nextDouble() * (max - min);
            this.pPosition[i] = randomCoordinate;
        }
    }

    void initVelocity() {
        pVelocity = new double[systemDimensions];
        for(int i = 0; i < systemDimensions; i++) {
            this.pVelocity[i] = this.randGen.nextDouble();
        }
    }
    
    void initValues() {
        pBestPosition = new double[systemDimensions];
        this.pBestValue = this.calculator.calculate(pPosition);
        System.arraycopy(pPosition, 0, pBestPosition, 0, systemDimensions);
    }

    double[] getPosition() {
        return this.pPosition;
    }

    void setGBestUniqueID(long particleID) {
        this.gBestUniqueId = particleID;
    }

    private void reevaluateVelocity() {
        double tmp_len = 0;
        double[] best = gPositions.get(gBestUniqueId);
        for(int i = 0; i < systemDimensions; i++) {
            double firstRandomFactor = (2 / standardPSOPsi) * randGen.nextDouble();
            double secondRandomFactor = (2 / standardPSOPsi) * randGen.nextDouble();
            //System.out.println("standardPSOBeta: " + standardPSOBeta);
            //System.out.println("pVelocity: " + Arrays.toString(pVelocity));
            //System.out.println("firstRandomFactor: " + firstRandomFactor);
            //System.out.println("pBestPosition: " + Arrays.toString(pBestPosition));
            //System.out.println("pPosition: " + Arrays.toString(pPosition));
            //System.out.println("secondRandomFactor: " + secondRandomFactor);
            //System.out.println("best: " + Arrays.toString(best));
            pVelocity[i] = standardPSOBeta * pVelocity[i]
                    + firstRandomFactor * (pBestPosition[i] - pPosition[i])
                    + secondRandomFactor * (best[i] - pPosition[i]);
        }
    }

    private void move() {
        for(int i = 0; i < systemDimensions; i++) {
            this.pPosition[i] += this.pVelocity[i];
        }
        System.out.println("#" + uniqueID + "V: " + Arrays.toString(pVelocity));
        System.out.println("#" + uniqueID + "P: " + Arrays.toString(pPosition));
    }

    private void reevaluateBests() {
        double tmpBestValue = this.calculator.calculate(pPosition);
        if(tmpBestValue < pBestValue) {
            this.pBestValue = tmpBestValue;
            System.arraycopy(pPosition, 0, pBestPosition, 0, systemDimensions);
            gPositions.put(uniqueID, pPosition);
        }
        if(tmpBestValue < calculator.calculate(gPositions.get(this.gBestUniqueId))) {
            setChanged();
            notifyObservers();
        }
        
    }
}
