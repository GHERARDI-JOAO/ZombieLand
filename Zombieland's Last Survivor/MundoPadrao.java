import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super classe para guardar dados referentes as configurações da tela.
 * 
 * @author (AJC_GAMES) 
 * @version (31/01/2025)
 */
public class MundoPadrao extends World
{
    
    /**
     * Constructor for objects of class MundoPadrao.
     * 
     */
    public MundoPadrao(int largura, int altura, int granularidade)
    {    
        super(largura, altura, granularidade); 
    }
    
    public void configurarFundo (Color cor){
        GreenfootImage backgroundImage = new GreenfootImage(getWidth(), getHeight());
        backgroundImage.setColor(cor);
        backgroundImage.fill();
        setBackground(backgroundImage);
    }
    
    public void configurarFundo (String nomeImagem){
        GreenfootImage backgroundImage = new GreenfootImage(nomeImagem);
        setBackground(backgroundImage);
    }
    
    public void adicionarElementoCentralizado (Actor ator, int posY){
        addObject(ator, getWidth() / 2, posY);
    }
    
    public void adicionarElemento (Actor ator, int posX, int posY){
        addObject(ator, posX, posY);
    }
}