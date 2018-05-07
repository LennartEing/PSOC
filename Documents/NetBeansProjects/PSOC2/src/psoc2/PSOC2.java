/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psoc2;

import util.ParticlePool;

/**
 *
 * @author leing
 */
public class PSOC2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParticlePool.setBenchMarkFunctionName("sphere");
        ParticlePool.setDifficultyFunctionName("flat");
        ParticlePool.setDifficultyStretchFactor(1);
        ParticlePool.setNumberOfParticles(32);
        ParticlePool.setPoolSize(8);
        ParticlePool.setSystemDimension(2);
        ParticlePool.setSystemPrecision(0.01);
        ParticlePool.setup();
        ParticlePool.instantiate();
        ParticlePool.execute();
    }
    
}
