/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
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
        int dimensions = 2;
        double bound = 10000;
        double[][] boundaries = new double[dimensions][2];
        for(int i = 0; i < dimensions; i++) {
            boundaries[i][0] = -bound;
            boundaries[i][1] = bound;
        }
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Long, double[]> config =
                new MutableConfiguration<>();
        Cache<Long, double[]> myCache = cacheManager.createCache("myCache", config);
        ParticleContainer container = ParticleContainer.instance();
        for(int i = 0; i < amount; i++) {
            Particle particle = new Particle(myCache, dimensions, boundaries);
            container.addParticle(particle);
        }
        container.start();
        try {
            PSOC.sleep(10000);
        } catch(Exception e) {
            System.out.println("This");
        }
        container.stop();
    }
}
