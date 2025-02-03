import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)
import java.util.ArrayList;

/**
 * Classe Tank representa o tanque de guerra controlado pelo jogador.
 * O tanque pode girar e atirar, mas não pode se mover.
 */
public class Tank extends Actor {
    // Imagem do tanque
    private GreenfootImage tankImage;

    // Variáveis de controle
    private long startTime;
    
    private int saude = 200;
    private int tempoInvencibilidade = 0;
    private static final int TEMPO_MAXIMO_INVENCIBILIDADE = 30;
    private HUD hud;

    

    /**
     * Construtor da classe Tank.
     * Inicializa a imagem do tanque e outros parâmetros.
     */
    public Tank(HUD hud) {
        tankImage = new GreenfootImage("Tank.png");
        tankImage.scale(120, 120); // Ajusta o tamanho da imagem
        setImage(tankImage); // Define a imagem inicial do tanque
        this.hud = hud;
        this.startTime = System.currentTimeMillis();
    }
    

    /**
     * Método principal executado a cada frame.
     */
    public void act() {
        girarParaMouse(); // Faz o tanque girar em direção ao mouse
        atirar(); // Permite disparar projéteis
        hud.atualizarSaude(saude);
        hud.exibirInformacoes();
        
            // Decrementa o tempo de invencibilidade, se necessário
        if (tempoInvencibilidade > 0) {
        tempoInvencibilidade--;
        }

        // Verifica se o tanque morreu
        verificarMorte();
}
    

    /**
     * Faz o tanque girar para a posição do mouse.
     */
   private void girarParaMouse() {
    MouseInfo mouse = Greenfoot.getMouseInfo();
    if (mouse != null) {
        int mouseX = mouse.getX();
        int mouseY = mouse.getY();
        
        // Calcula o ângulo entre o tanque e o mouse
        int angulo = (int) Math.toDegrees(Math.atan2(getY() - mouseY, getX() - mouseX));

        // Aplica a rotação
        setRotation(angulo - 90);
    }
}
    /**
     * Permite o tanque disparar projéteis (com munição infinita).
     */
    private void atirar() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null && Greenfoot.mouseClicked(null)) {
                int ajusteX =  10;
                int ajusteY = -40;

                double angleRadians = Math.toRadians(getRotation());
                int offsetX = (int) (ajusteX * Math.cos(angleRadians) - ajusteY * Math.sin(angleRadians));
                int offsetY = (int) (ajusteX * Math.sin(angleRadians) + ajusteY * Math.cos(angleRadians));

                getWorld().addObject(new TankBullet(getRotation()), getX() + offsetX, getY() + offsetY);
        }
    }
    
    public void receberDano() {
        if (tempoInvencibilidade == 0) {
            saude -= 10;
            tempoInvencibilidade = TEMPO_MAXIMO_INVENCIBILIDADE;
        }
    }
    
        private void verificarMorte() {
            getSaude();
            int saudeRestante = getSaude();
        if (saude <= 0) {
            Greenfoot.setWorld(new TelaFinal(getSaude()));
            //getWorld().showText("Game Over!", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
    }
    
        public int getSaude() {
        return saude;
    }

}
