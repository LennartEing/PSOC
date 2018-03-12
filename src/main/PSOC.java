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
        int dimensions = 30;
        double bound = 5.12;
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Long, double[]> config =
                new MutableConfiguration<>();
        Cache<Long, double[]> myCache = cacheManager.createCache("myCache", config);
        ParticleContainer container = ParticleContainer.instance();
        for(int i = 0; i < amount; i++) {
            Particle particle = new Particle(myCache, dimensions, bound);
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
