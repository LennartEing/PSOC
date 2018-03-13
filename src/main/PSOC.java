/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import excep.MissingFunctionException;
import java.util.ArrayList;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import util.Calculator;
import util.CalculatorFactory;
import util.Particle;
import util.ParticleContainer;

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
        double boundValue = 5.12;
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Long, double[]> config =
                new MutableConfiguration<>();
        Cache<Long, double[]> myCache = cacheManager.createCache("myCache", config);
        ParticleContainer container = ParticleContainer.instance();
        CalculatorFactory calculatorFactory = new CalculatorFactory("rosenbrock", "gaussian", boundValue, timeFactor);
        for(int i = 0; i < amount; i++) {
            Calculator calculator = null;
            try {
                calculator = calculatorFactory.getCalculator();
                if(calculator == null) {
                    System.out.println("The Calculator you were trying to create didnt exist");
                }
            } catch (MissingFunctionException e) {
                System.out.println("The Function you were trying to create didnt exist");
            }
            Particle particle = new Particle(myCache, dimensions, calculator, boundValue);
            container.addParticle(particle);
        }
        container.start();
        try {
            PSOC.sleep(10000);
        } catch(InterruptedException e) {
            System.out.println("Unable to make mainprocess go to sleep. Teenagers, huh?");
        }
        container.stop();
    }
}
