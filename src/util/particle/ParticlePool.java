package util.particle;


import util.particle.Particle;
import abstracts.DifficultyFunction;
import abstracts.FitnessFunction;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leing
 * @param <T>
 * @param <K>
 */
public class ParticlePool <T extends FitnessFunction, K extends DifficultyFunction> implements Observer {
    
    private ArrayList<Particle> particles = new ArrayList();
    private final ExecutorService threadpool;
    private boolean found = false;
    
    public ParticlePool(int threadNumber) {
        this.threadpool = Executors.newFixedThreadPool(threadNumber);
    }
    
    public Particle getParticle(int index) {
        return this.particles.get(index);
    }    
    
    public void setParticle(int index, Particle particle) {
        this.particles.set(index, particle);
    }
    
    public boolean addParticle(Particle particle) {
        if(this.particles.contains(particle)) return false;
        particle.addObserver(this);
        this.particles.add(particle);
        return true;
    }
    
    public boolean removeParticle(Particle particle) {
        if(!this.particles.contains(particle)) return false;
        particle.deleteObserver(this);
        this.particles.remove(particle);
        return true;
    }
    
    public void run() {
        while(!found) {
            for(Particle particle : particles) {
                threadpool.execute(particle);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        
        System.out.println("Changed from " + ((Particle)o).getGBestThreadId() + " to :" + Long.toString((long) arg));
        for(Particle particle : particles) {
            particle.setGBestThreadId((long) arg);
        }
    }
}
