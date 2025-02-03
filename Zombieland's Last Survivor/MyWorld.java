import greenfoot.*;
import java.util.ArrayList; 

public class MyWorld extends MundoPadrao {
    private ArrayList<Zombie> zumbis; // Lista de zumbis ativos
    private int maxZumbis = 50; // Número máximo de zumbis na tela
    private int zombieSpawnCounter = 0;
    private int zombieSpawnInterval = 120;
    private int bulletSpawnCounter = 0; // Contador para spawn de munições
    private int bulletSpawnInterval = 500; // Spawn de munições a cada 500 frames

    private int survivalTime = 0; // Temporizador para rastrear o tempo de sobrevivência (em frames)

    private GreenfootSound horrorAmbienceSound;
    private GreenfootSound zombieSound;

    private int zombieSpawnTimer = 0; // Contador de tempo para spawn adicional
    private int spawnInterval = 1800; // 30 segundos em ciclos (60 ciclos/segundo)
    private int additionalZombies = 5; // Número adicional de zumbis para spawnar a cada intervalo

    private HUD hud;

    public MyWorld() {    
        super(1920, 1080, 1); 
        zumbis = new ArrayList<>(); // Inicializa a lista de zumbis

        hud = new HUD(this, System.currentTimeMillis(), 100);

        GreenfootImage backgroundImage = new GreenfootImage("backgrounddetailed2.png");
        setBackground(backgroundImage);
        
        SoldadoSUPERBLAZER soldado = new SoldadoSUPERBLAZER(hud);
        addObject(soldado, 400, 300);

        horrorAmbienceSound = new GreenfootSound("mixkit-horror-ambience-2482.wav");
        zombieSound = new GreenfootSound("ZombieSound.wav");

        horrorAmbienceSound.playLoop();
        zombieSound.playLoop();
    }

public void act() {
    // Verifica se é hora de spawnar um zumbi
    verificarSpawnZumbi();

    // Verifica se é hora de spawnar munição
    verificarSpawnMunicao();

    // Verifica se é hora de spawnar uma nova onda de zumbis
    verificarOndaAdicional();

    // Verifica se o tempo de sobrevivência foi atingido
    verificarTempoSobrevivencia();
}

private void verificarSpawnZumbi() {
    zombieSpawnCounter++;
    if (zombieSpawnCounter >= zombieSpawnInterval) {
        spawnZombie();
        zombieSpawnCounter = 0;
    }
}

private void verificarSpawnMunicao() {
    bulletSpawnCounter++;
    if (bulletSpawnCounter >= bulletSpawnInterval) {
        spawnBulletPickup();
        bulletSpawnCounter = 0;
    }
}

private void verificarOndaAdicional() {
    zombieSpawnTimer++;
    if (zombieSpawnTimer >= spawnInterval) {
        spawnAdditionalZombies();
        zombieSpawnTimer = 0;
    }
}

private void verificarTempoSobrevivencia() {
    survivalTime++;
    if (survivalTime >= 120 * 60) { // 120 segundos * 60 frames por segundo
        showVictoryMessage();
    }
}

    private void spawnZombie() {
            
        if (zumbis.size() >= maxZumbis) {
        return; // Não spawna mais zumbis
        }   
        
        Zombie zombie = new ZombieBasic();
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

    private void spawnBulletPickup() {
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        addObject(new BulletPickup(), x, y);
    }
    
        public void pararSons() {
        horrorAmbienceSound.stop();
        zombieSound.stop();
    }
    
    private void spawnAdditionalZombies() {
        for (int i = 0; i < additionalZombies; i++) {
            
            if (zumbis.size() >= maxZumbis) {
            return; // Não spawna mais zumbis
        }
        
            Zombie novoZombie = new ZombieBasic();
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

        private void showVictoryMessage() {
        SoldadoSUPERBLAZER jogador = (SoldadoSUPERBLAZER) getObjects(SoldadoSUPERBLAZER.class).get(0);
        int saudeRestante = jogador.getSaude();
    
        GerenciadorVitoria gerenciador = new GerenciadorVitoria(this);
        gerenciador.mostrarMensagemVitoria(saudeRestante, "Soldado");
    
        Greenfoot.delay(250);
        Greenfoot.setWorld(new SecondPhase());
    }
}
