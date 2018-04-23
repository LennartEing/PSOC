/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.particle;

import javax.cache.Cache;
import util.calc.CalculatorFactory;

/**
 *
 * @author leing
 */
public class ParticleConfiguration {
    
    private final Cache<Long, double[]> cache;
    private final int dimensions;
    private final CalculatorFactory calculatorFactory;
    private final double boundValue;
    private final double precision;
    
    public ParticleConfiguration(Cache<Long, double[]> cache, int dimensions, CalculatorFactory calculatorFactory, double boundValue, double precision) {
        this.cache = cache;
        this.dimensions = dimensions;
        this.calculatorFactory = calculatorFactory;
        this.boundValue = boundValue;
        this.precision = precision;
    }
    
    public Cache<Long, double[]> getCache() {
        return this.cache;
    }
    public int getDimensions() {
        return this.dimensions;
    }
    public CalculatorFactory getCalculatorFactory() {
        return this.calculatorFactory;
    }
    public double getBoundValue() {
        return this.boundValue;
    }
    public double getPrecision() {
        return this.precision;
    }
}
