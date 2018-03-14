/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.particle;

import excep.MissingFunctionException;
import javax.cache.Cache;
import util.calc.Calculator;
import util.calc.CalculatorFactory;

/**
 *
 * @author leing
 */
public final class ParticleFactory {
    
    private ParticleConfiguration config;
    
    public ParticleFactory(Cache<Long, double[]> cache, int dimensions, CalculatorFactory calculatorFactory, double boundValue) {
        this.configure(cache, dimensions, calculatorFactory, boundValue);
    }
    
    public void configure(Cache<Long, double[]> cache, int dimensions, CalculatorFactory calculator, double boundValue) {
        config = new ParticleConfiguration(cache, dimensions, calculator, boundValue);
    }
 
    public Particle getParticle() throws MissingFunctionException {
        Calculator calculator = this.config.getCalculatorFactory().getCalculator();
        return new Particle(this.config.getCache(), this.config.getDimensions(), calculator, this.config.getBoundValue());
    }
}
