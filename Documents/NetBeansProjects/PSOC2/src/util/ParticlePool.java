/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

/**
 *
 * @author leing
 */
public class ParticlePool implements Observer {
    
    private final ExecutorService threadPool;
    private final Cache<Long, double[]> gPositions;
    private final Random randomGen = new Random();
    
    private ArrayList<Particle> particles = new ArrayList();
    private boolean found = false;
    
    public ParticlePool(int poolSize, int numberOfParticles, double systemPrecision, int systemDimensions, String benchmarkFunctionName, String difficultyFunctionName, int timeStretchFactor) {
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Long, double[]> config =
                new MutableConfiguration<>();
        this.gPositions = cacheManager.createCache("myCache", config);
        this.threadPool = Executors.newFixedThreadPool(poolSize);
        Calculator calculatorOrigin = new Calculator();
        for(int i = 0; i < numberOfParticles; i++) {
            Calculator calculator = calculatorOrigin.getCalculator(benchmarkFunctionName, difficultyFunctionName, timeStretchFactor);
            Particle particle = new Particle(randomGen.nextLong(), systemPrecision, systemDimensions, calculator, this.gPositions);
            particle.initParticlePosition();
            particle.addObserver(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
