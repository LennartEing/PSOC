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
    
    private static int poolSize;
    private static int numberOfParticles;
    private static double systemPrecision;
    private static int systemDimensions;
    private static String benchmarkFunctionName;
    private static String difficultyFunctionName;
    private static int difficultyStretchFactor;
    
    private static ExecutorService threadPool;
    private static Cache<Long,double[]> gPositions;
    private static Random randGen;
    private static ArrayList<Particle> particles;
    private static long[] particleIDs;
    
    private boolean precisionMet = false;
    
    private static ParticlePool particlePool;
    
    public ParticlePool() {
        generate();
    }
    
    private void generate() {
        Calculator calculatorOrigin = new Calculator();
        for(int i = 0; i < numberOfParticles; i++) {
            Calculator calculator = calculatorOrigin.getCalculator(benchmarkFunctionName, difficultyFunctionName, difficultyStretchFactor);
            long particleID = randGen.nextLong();
            particleIDs[i] = particleID;
            Particle particle = new Particle();
            particle.setUniqueID(Math.abs(particleID));
            particle.setSystemPrecision(systemPrecision);
            particle.setSystemDimensions(systemDimensions);
            particle.setCalculator(calculator);
            particle.setGPositions(gPositions);
            particle.initPosition();
            particle.initVelocity();
            particle.initValues();
            gPositions.put(particleID, particle.getPosition());
            particles.add(particle);
        }
        for(Particle particle : particles) {
            particle.setGBestUniqueID(particleIDs[randGen.nextInt(numberOfParticles)]);
            particle.addObserver(this);
        }
    }
    
    public static void instantiate() {
        ParticlePool.particlePool = new ParticlePool();
    }
    
    public static void setPoolSize(int x) {
        ParticlePool.poolSize = x;
    }
    
    public static void setNumberOfParticles(int x) {
        ParticlePool.numberOfParticles = x;
    }
    
    public static void setSystemPrecision(double x) {
        ParticlePool.systemPrecision = x;
    }
    
    public static void setSystemDimension(int x) {
        ParticlePool.systemDimensions = x;
    }
    
    public static void setBenchMarkFunctionName(String x) {
        ParticlePool.benchmarkFunctionName = x;
    }
    
    public static void setDifficultyFunctionName(String x) {
        ParticlePool.difficultyFunctionName = x;
    }
    
    public static void setDifficultyStretchFactor(int x) {
        ParticlePool.difficultyStretchFactor = x;
    }
    
    public static void setup() {
        threadPool = Executors.newFixedThreadPool(poolSize);
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Long, double[]> config = new MutableConfiguration<>();
        gPositions = cacheManager.createCache("particlePositions", config);
        randGen = new Random();
        particles = new ArrayList();
        particleIDs = new long[numberOfParticles];
    }
    
    public static long execute() {
        long startTime = System.nanoTime();
        particlePool.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    
    private void run() {
        while(!precisionMet) {
            for(Particle particle : particles) {
                if(particle.isRunning == false) {
                    particle.isRunning = true;
                    threadPool.execute(particle);
                }
            }
        }
        threadPool.shutdown();
        while(!threadPool.isTerminated()) System.out.println("Waiting for shutdown");
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Boolean) {
            this.precisionMet = true;
        }
        if(arg instanceof Long) {
            Particle caller = (Particle) o;
            caller.setGBestUniqueID(particleIDs[randGen.nextInt(numberOfParticles)]);
            for(Particle particle : particles) {
                particle.gBestUniqueId = (long) arg;
            }
        }
    }            
}
