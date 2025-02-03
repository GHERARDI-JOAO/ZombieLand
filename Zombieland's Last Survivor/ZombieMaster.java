import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZombieMaster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombieMaster extends Zombie {
    public ZombieMaster() {
        super();
        velocidade = 5; 
        
        for (int i = 0; i < idleDireita.length; i++) {
            idleDireita[i].scale(140, 160);
            idleEsquerda[i].scale(140, 160);
        }
        for (int i = 0; i < runDireita.length; i++) {
            runDireita[i].scale(140, 160);
            runEsquerda[i].scale(140, 160);
        }
    }
    
}