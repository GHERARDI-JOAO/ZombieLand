import greenfoot.*;

public class GerenciadorVitoria {
    private World mundo;
    private GreenfootSound victoryMusic;

    public GerenciadorVitoria(World mundo) {
        this.mundo = mundo;
        this.victoryMusic = new GreenfootSound("musica.mp3");
    }

    public void mostrarMensagemVitoria(int saudeRestante, String tipoJogador) {
        // Para os sons do jogo
        if (mundo instanceof MyWorld) {
            ((MyWorld) mundo).pararSons();
        } else if (mundo instanceof SecondPhase) {
            ((SecondPhase) mundo).pararSons();
        }

        // Toca a música de vitória
        victoryMusic.playLoop();

        // Define a mensagem de vitória baseada na saúde restante
        String mensagemVitoria;
        if (tipoJogador.equals("Soldado")) {
            mensagemVitoria = definirMensagemSoldado(saudeRestante);
        } else if (tipoJogador.equals("Tanque")) {
            mensagemVitoria = definirMensagemTanque(saudeRestante);
        } else {
            mensagemVitoria = "Vitória!";
        }

        // Exibe a mensagem de vitória e a quantidade de vida restante
        mundo.showText(mensagemVitoria, mundo.getWidth() / 2, mundo.getHeight() / 2 - 20);
        mundo.showText("Saúde restante: " + saudeRestante, mundo.getWidth() / 2, mundo.getHeight() / 2 + 20);

        Greenfoot.delay(250);

        // Limpa as mensagens
        mundo.showText("", mundo.getWidth() / 2, mundo.getHeight() / 2 - 30);
        mundo.showText("", mundo.getWidth() / 2, mundo.getHeight() / 2 + 20);
    }

    private String definirMensagemSoldado(int saudeRestante) {
        if (saudeRestante == 100) {
            return "Incrível! Você sobreviveu com saúde perfeita! Um verdadeiro herói!";
        } else if (saudeRestante >= 70) {
            return "Ótimo trabalho! Você sobreviveu com saúde considerável! Sucesso na evacuação!";
        } else if (saudeRestante >= 50) {
            return "Boa resistência! Apesar dos ferimentos, você sobreviveu!";
        } else if (saudeRestante >= 20) {
            return "Foi por pouco! Você está gravemente ferido, mas conseguiu sobreviver!";
        } else {
            return "Que luta! Sobreviveu por um triz. Cada segundo valeu a pena!";
        }
    }

    private String definirMensagemTanque(int saudeRestante) {
        if (saudeRestante >= 180) {
            return "Incrível! O tanque está quase intacto! Você é um verdadeiro mestre!";
        } else if (saudeRestante >= 140) {
            return "Ótima performance! O tanque ainda está em boas condições!";
        } else if (saudeRestante >= 100) {
            return "Boa estratégia! O tanque sofreu alguns danos mas ainda está operacional!";
        } else if (saudeRestante >= 60) {
            return "Por pouco! O tanque está bastante danificado, mas completou a missão!";
        } else {
            return "Incrível resistência! O tanque está muito danificado, mas vencemos!";
        }
    }
}