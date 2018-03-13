package util;


import abstracts.DifficultyFunction;
import abstracts.FitnessFunction;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

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
public class ParticleContainer <T extends FitnessFunction, K extends DifficultyFunction> implements Observer {
    
    private ArrayList<Particle> particles = new ArrayList();
    private static ParticleContainer instance = null;
    private final Random randomGen = new Random();
    
    protected ParticleContainer() {}
    
    public static ParticleContainer instance() {
        if(ParticleContainer.instance == null) {
            ParticleContainer.instance = new ParticleContainer();
        }
        return ParticleContainer.instance;
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
    
    public void start() {
        long tmpThreadId = particles.get((int)(randomGen.nextDouble() * particles.size())).getThreadId();
        for(Particle particle : particles) {
            particle.setGBestThreadId(tmpThreadId);
        }
        for(Particle p : particles) {
            p.start();
        }
    }
    
    public void stop() {
        for(Particle p : particles) {
            p.stop();
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
