/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import excep.MissingFunctionException;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import util.calc.Calculator;
import util.calc.CalculatorFactory;
import util.particle.Particle;
import util.particle.ParticlePool;
import util.particle.ParticleFactory;

/**
 *
 * @author leing
 */
public class PSOC extends Thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int amount = 20;
        int dimensions = 30;
        int timeFactor = 1;
        double boundValue = 2.048;
        int testTime = 100000;
        double precision = 0.1;
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Long, double[]> config =
                new MutableConfiguration<>();
        Cache<Long, double[]> myCache = cacheManager.createCache("myCache", config);
        ParticlePool pool = new ParticlePool(5);
        CalculatorFactory calculatorFactory = new CalculatorFactory("styblinskitang", "flat", boundValue, timeFactor);
        ParticleFactory particleFactory = new ParticleFactory(myCache, dimensions, calculatorFactory, boundValue, precision);
        for(int i = 0; i < amount; i++) {
            try {
                Particle particle = particleFactory.getParticle();
                pool.addParticle(particle);
            } catch(MissingFunctionException e) {
                System.out.println("Unimplemented function used");
            }
        }
        pool.run();
        try {
            PSOC.sleep(testTime);
        } catch(InterruptedException e) {
            System.out.println("Unable to make mainprocess go to sleep. Teenagers, huh?");
        }
    }
}
