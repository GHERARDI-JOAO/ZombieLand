import greenfoot.*;

public class TelaFinal extends MundoPadrao {
    private int saudeRestante;

    public TelaFinal(int saude) { 
        super(1920, 1080, 1);
        this.saudeRestante = saude;

        // Configura fundo preto usando greenfoot.Color
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(new greenfoot.Color(0, 0, 0)); // Preto
        background.fill();
        setBackground(background);

        // Configura mensagem
        String mensagem;
        if (saudeRestante > 0) {
            mensagem = "Você venceu!! Saúde restante: " + saudeRestante +" \n 4 minutos sobrevividos" + "\nPressione R para reiniciar";
        } else {
            mensagem = "Game Over! Pressione R para reiniciar";
        }

        // Cria texto com greenfoot.Color
        GreenfootImage texto = new GreenfootImage(
            mensagem, 
            36, 
            new greenfoot.Color(255, 255, 255), // Branco
            new greenfoot.Color(0, 0, 0, 0)     // Transparente
        );

        // Centraliza o texto
        int x = (getWidth() - texto.getWidth()) / 2;
        int y = (getHeight() - texto.getHeight()) / 2;
        getBackground().drawImage(texto, x, y);
    }

    public void act() {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new TelaInicial()); // Reinicia o jogo
        }
    }
}