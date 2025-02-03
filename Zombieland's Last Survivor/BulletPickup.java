import greenfoot.*;  

public class BulletPickup extends Actor {

    /**
     * Construtor da classe BulletPickup.
     * Inicializa o objeto de pickup de bala com a imagem e o tamanho adequado.
     */
    public BulletPickup() {
        // Carrega a imagem do ícone de pickup de bala
        GreenfootImage image = new GreenfootImage("bullet.png");  // Usando uma imagem personalizada para o pickup

        // Ajusta o tamanho da imagem do pickup
        image.scale(40, 40);  // Redimensiona a imagem para 40x40 pixels

        // Define a imagem do ator para o pickup
        setImage(image);
    }
}
