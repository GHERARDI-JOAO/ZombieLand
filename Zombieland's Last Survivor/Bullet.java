import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe Bullet representa projéteis disparados pelo jogador.
 * Eles se movem na direção definida e podem eliminar zumbis ao colidir.
 */
public class Bullet extends Actor {
    private int speed = 10; // Velocidade de movimento da bala

    /**
     * Construtor da classe Bullet.
     * 
     * @param rotation Rotação inicial da bala, define a direção do disparo.
     */
    public Bullet(int rotation) {
        setRotation(rotation); // Define a direção inicial do projétil

        // Redimensiona a imagem da bala para um tamanho adequado
        GreenfootImage image = getImage();
        image.scale(10, 10); // Ajusta o tamanho da bala
        setImage(image);

        // Reproduz o som de disparo ao criar a bala
        Greenfoot.playSound("mixkit-game-gun-shot-1662 (online-audio-converter.com).wav");
    }

    /**
     * Método chamado em cada ciclo do jogo.
     * Move a bala e verifica colisões com zumbis ou se ela saiu da tela.
     */
    public void act() {
        move(speed); // Move a bala na direção configurada

        // Verifica se a bala colidiu com um zumbi
        Zombie zombieHit = (Zombie) getOneIntersectingObject(Zombie.class);
        if (zombieHit != null) {
            // Remove o zumbi e a bala do mundo
            getWorld().removeObject(zombieHit);
            getWorld().removeObject(this);
            return; // Interrompe o restante do método
        }

        verificarRemocao(); // Remove a bala se ela sair dos limites da tela
    }

    /**
     * Verifica se a bala está fora dos limites do mundo e a remove.
     */
    private void verificarRemocao() {
        if (getX() >= getWorld().getWidth() - 1 || getX() < 1 || 
            getY() >= getWorld().getHeight() - 1 || getY() < 1) {
            getWorld().removeObject(this); // Remove a bala do mundo
        }
    }
}
