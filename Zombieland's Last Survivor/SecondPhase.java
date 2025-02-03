import greenfoot.*;
import java.util.ArrayList;

/**
 * A segunda fase do jogo, onde o jogador controla o tanque e enfrenta hordas de zumbis.
 */
public class SecondPhase extends MundoPadrao {
    private ArrayList<Zombie> zumbis; // Lista de zumbis ativos
    private int maxZumbis = 150; // Número máximo de zumbis na tela
    private GreenfootSound horrorAmbienceSound;
    private GreenfootSound zombieSound;
    private int zombieSpawnCounter = 0;
    private int zombieSpawnInterval = 60;
    private int zombieSpawnTimer = 0; // Contador de tempo para spawn adicional
    private int spawnInterval = 1800; // 30 segundos em ciclos (60 ciclos/segundo)
    private int additionalZombies = 20; // Número adicional de zumbis para spawnar a cada intervalo
    private int survivalTime = 0; // Temporizador para rastrear o tempo de sobrevivência (em frames)
    private HUD hud;
    private boolean mostrarMensagemInicial = true;
    private int tempoMensagemInicial = 380; // 3 segundos (60 frames por segundo)
    private GerenciadorVitoria gerenciadorVitoria; // Adiciona o gerenciador de vitória

    /**
     * Construtor para a fase do tanque.
     */
    public SecondPhase() {      
        super(1920, 1080, 1); 
        zumbis = new ArrayList<>(); 

        configurarFundo("backgrounddetailed2.png"); // Configura o fundo do mundo

        hud = new HUD(this, System.currentTimeMillis(), 200);
        Tank tank = new Tank(hud);
        addObject(tank, getWidth() / 2, getHeight() / 2);

        horrorAmbienceSound = new GreenfootSound("mixkit-horror-ambience-2482.wav");
        zombieSound = new GreenfootSound("ZombieSound.wav");

        horrorAmbienceSound.playLoop();
        zombieSound.playLoop();

        gerenciadorVitoria = new GerenciadorVitoria(this); // Inicializa o gerenciador de vitória
        
        exibirMensagemInicial(); // Exibe a mensagem inicial
    }

    /**
     * O método act() é chamado a cada frame do jogo.
     */
    public void act() {
        if (mostrarMensagemInicial) {
            exibirMensagemInicial();
            return;
        }

        // Verifica se é hora de spawnar um zumbi
        verificarSpawnZumbi();

        // Verifica se é hora de spawnar uma nova onda de zumbis
        verificarOndaAdicional();

        // Verifica se o tempo de sobrevivência foi atingido
        verificarTempoSobrevivencia();
    }

    /**
     * Exibe a mensagem inicial por alguns segundos.
     */
    private void exibirMensagemInicial() {
        // Primeira linha
        showText("Você conseguiu enfrentar as hordas de zumbis, mas agora encontrou um tanque quebrado com balas infinitas.", 
                getWidth() / 2, getHeight() / 2 - 40);
        
        // Segunda linha
        showText("Você está cercado por zumbis mais velozes e fortes, e precisa enfrentá-los, mesmo estando preso no tanque.", 
                getWidth() / 2, getHeight() / 2);
        
        // Terceira linha
        showText("Boa sorte!", 
                getWidth() / 2, getHeight() / 2 + 40);
            
        tempoMensagemInicial--;
        if (tempoMensagemInicial <= 0) {
            // Limpa todas as linhas
            showText("", getWidth() / 2, getHeight() / 2 - 40);
            showText("", getWidth() / 2, getHeight() / 2);
            showText("", getWidth() / 2, getHeight() / 2 + 40);
            mostrarMensagemInicial = false;
        }
    }

    /**
     * Verifica se é hora de spawnar um novo zumbi.
     */
    private void verificarSpawnZumbi() {
        zombieSpawnCounter++;
        if (zombieSpawnCounter >= zombieSpawnInterval) {
            spawnZombie();
            zombieSpawnCounter = 0;
        }
    }

    /**
     * Spawna um novo zumbi em uma das quatro direções da tela.
     */
    private void spawnZombie() {
        if (zumbis.size() >= maxZumbis) {
            return; // Não spawna mais zumbis
        }   

        Zombie zombie = new ZombieMaster();
        int spawnSide = Greenfoot.getRandomNumber(4);

        switch(spawnSide) {
            case 0:
                addObject(zombie, Greenfoot.getRandomNumber(getWidth()), 0);
                zombie.setRotation(90 + Greenfoot.getRandomNumber(180));
                break;
            case 1:
                addObject(zombie, Greenfoot.getRandomNumber(getWidth()), getHeight() - 1);
                zombie.setRotation(-90 + Greenfoot.getRandomNumber(180));
                break;
            case 2:
                addObject(zombie, 0, Greenfoot.getRandomNumber(getHeight()));
                zombie.setRotation(Greenfoot.getRandomNumber(180));
                break;
            case 3:
                addObject(zombie, getWidth() - 1, Greenfoot.getRandomNumber(getHeight()));
                zombie.setRotation(180 + Greenfoot.getRandomNumber(180));
                break;
        }
        zumbis.add(zombie); // Adiciona o zumbi à lista
    }
    
       public void removerZumbi(Zombie zombie) {
        zumbis.remove(zombie); // Remove o zumbi da lista
    }

    /**
     * Verifica se é hora de spawnar uma nova onda de zumbis.
     */
    private void verificarOndaAdicional() {
        zombieSpawnTimer++;
        if (zombieSpawnTimer >= spawnInterval) {
            spawnAdditionalZombies();
            zombieSpawnTimer = 0;
        }
    }

    /**
     * Spawna zumbis adicionais a cada 30 segundos.
     */
    private void spawnAdditionalZombies() {
        for (int i = 0; i < additionalZombies; i++) {
            if (zumbis.size() >= maxZumbis) {
                return; // Não spawna mais zumbis
            }
            Zombie novoZombie = new ZombieMaster();
            int spawnSide = Greenfoot.getRandomNumber(4);

            switch(spawnSide) {
                case 0:
                    addObject(novoZombie, Greenfoot.getRandomNumber(getWidth()), 0);
                    novoZombie.setRotation(90 + Greenfoot.getRandomNumber(180));
                    break;
                case 1:
                    addObject(novoZombie, Greenfoot.getRandomNumber(getWidth()), getHeight() - 1);
                    novoZombie.setRotation(-90 + Greenfoot.getRandomNumber(180));
                    break;
                case 2:
                    addObject(novoZombie, 0, Greenfoot.getRandomNumber(getHeight()));
                    novoZombie.setRotation(Greenfoot.getRandomNumber(180));
                    break;
                case 3:
                    addObject(novoZombie, getWidth() - 1, Greenfoot.getRandomNumber(getHeight()));
                    novoZombie.setRotation(180 + Greenfoot.getRandomNumber(180));
                    break;
            }
            zumbis.add(novoZombie); // Adiciona o zumbi à lista
        }
        additionalZombies++; // Aumenta o número de zumbis para a próxima onda
    }

    /**
     * Verifica se o tempo de sobrevivência foi atingido.
     */
    private void verificarTempoSobrevivencia() {
        survivalTime++;
        if (survivalTime >= 120 * 60) { // 120 segundos * 60 frames por segundo
            showVictoryMessage();
        }
    }

    /**
     * Exibe a mensagem de vitória e finaliza o jogo.
     */
    private void showVictoryMessage() {
        Tank tank = (Tank) getObjects(Tank.class).get(0);
        int saudeRestante = tank.getSaude();

        // Usa o GerenciadorVitoria para exibir a mensagem de vitória
        gerenciadorVitoria.mostrarMensagemVitoria(saudeRestante, "Tanque");

        Greenfoot.delay(250);
        Greenfoot.setWorld(new TelaFinal(tank.getSaude()));
    }

    /**
     * Para os sons do jogo.
     */
    public void pararSons() {
        horrorAmbienceSound.stop();
        zombieSound.stop();
    }
}