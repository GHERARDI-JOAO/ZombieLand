import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

/**
 * Classe SoldadoSUPERBLAZER representa o personagem principal controlado pelo jogador.
 * Ele pode se mover, atirar, interagir com pickups e exibir informações na tela.
 */
public class SoldadoSUPERBLAZER extends Actor {
    // Arrays de animação
    private GreenfootImage[] idleDireita;
    private GreenfootImage[] idleEsquerda;
    private GreenfootImage[] runDireita;
    private GreenfootImage[] runEsquerda;

    // Controle de animação
    private int indiceImagemAtual;
    private int passosParaAtualizarAImagem;
    private int passosDesdeUltimaAtualizacaoImagem;

    // Variáveis de saúde e invencibilidade
    private int saude = 100;
    private int tempoInvencibilidade = 0;
    private static final int TEMPO_MAXIMO_INVENCIBILIDADE = 30; // Frames de invencibilidade após dano

    // Sons e movimentação
    private GreenfootSound walkingSound;
    private boolean isMoving = false; // Flag para indicar se o personagem está se movendo

    private HUD hud; // Adiciona uma referência ao HUD

    // Controle de munição
    private int bullets = 10; // Inicia com 10 balas

    // Tempo de sobrevivência
    private long startTime;

    /**
     * Construtor da classe SoldadoSUPERBLAZER.
     * Inicializa animações, sons e outros parâmetros do soldado.
     */
    public SoldadoSUPERBLAZER(HUD hud) {
        passosParaAtualizarAImagem = 5; // Intervalo de atualização da animação
        passosDesdeUltimaAtualizacaoImagem = 0;
        
        this.hud = hud; // HUD inicializaçao
        // Inicializa os arrays de animaçao
        idleDireita = carregarImagens("survivor-idle_rifle_", 20, false);
        idleEsquerda = carregarImagens("survivor-idle_rifle_", 20, true);
        runDireita = carregarImagens("survivor-move_rifle_", 20, false);
        runEsquerda = carregarImagens("survivor-move_rifle_", 20, true);

        indiceImagemAtual = 0;
        setImage(idleDireita[indiceImagemAtual]); // Define imagem inicial

        startTime = System.currentTimeMillis(); // Marca o tempo inicial
        walkingSound = new GreenfootSound("mixkit-walking-on-grass-1921.wav"); // Som de movimento
    }

    /**
     * Método principal executado a cada frame.
     */
    public void act() {
        moverParaMouse(); // Movimenta o soldado em direção ao mouse
        atualizarAnimacao(); // Atualiza a animação
        atirar(); // Permite disparar projéteis
        verificarColisaoComBulletPickup(); // Verifica se o soldado pegou munição

        // Atualiza tempo de invencibilidade
        if (tempoInvencibilidade > 0) {
            tempoInvencibilidade--;
        }

        verificarMorte(); // Verifica se o soldado morreu

        // Controla o som de movimento
        if (isMoving) {
            if (!walkingSound.isPlaying()) {
                walkingSound.playLoop();
            }
        } else {
            if (walkingSound.isPlaying()) {
                walkingSound.stop();
            }
        }

        // Exibe informações na tela
        hud.atualizarSaude(saude);
        hud.atualizarBalas(bullets);
        hud.exibirInformacoes();
    }

    /**
     * Carrega imagens para animações.
     * 
     * @param prefixo Prefixo do arquivo de imagem.
     * @param quantidade Quantidade de imagens.
     * @param espelhar Se true, espelha as imagens horizontalmente.
     * @return Vetor de imagens carregadas.
     */
    private GreenfootImage[] carregarImagens(String prefixo, int quantidade, boolean espelhar) {
        GreenfootImage[] imagens = new GreenfootImage[quantidade];
        for (int i = 0; i < quantidade; i++) {
            imagens[i] = new GreenfootImage(prefixo + i + ".png");
            imagens[i].scale(120, 140); // Ajusta o tamanho da imagem
            if (espelhar) {
                imagens[i].mirrorHorizontally();
            }
        }
        return imagens;
    }

    /**
     * Atualiza a animação do soldado.
     */
    private void atualizarAnimacao() {
        passosDesdeUltimaAtualizacaoImagem++;
        if (passosDesdeUltimaAtualizacaoImagem >= passosParaAtualizarAImagem) {
            passosDesdeUltimaAtualizacaoImagem = 0;
            indiceImagemAtual = (indiceImagemAtual + 1) % 20;
            if (isMoving) {
                setImage(runDireita[indiceImagemAtual]);
            } else {
                setImage(idleDireita[indiceImagemAtual]);
            }
        }
    }

    /**
     * Move o soldado em direção ao mouse.
     */
    private void moverParaMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            turnTowards(mouseX, mouseY);

            int deltaX = mouseX - getX();
            int deltaY = mouseY - getY();
            double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distancia > 5) {
                move(2);
                isMoving = true;
            } else {
                isMoving = false;
            }
        }
    }

    /**
     * Permite o soldado disparar projéteis se houver munição.
     */
    private void atirar() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null && Greenfoot.mouseClicked(null) && bullets > 0) {
            int ajusteX = 50;
            int ajusteY = 39;

            double angleRadians = Math.toRadians(getRotation());
            int offsetX = (int) (ajusteX * Math.cos(angleRadians) - ajusteY * Math.sin(angleRadians));
            int offsetY = (int) (ajusteX * Math.sin(angleRadians) + ajusteY * Math.cos(angleRadians));

            getWorld().addObject(new Bullet(getRotation()), getX() + offsetX, getY() + offsetY);
            bullets--;
            hud.atualizarBalas(bullets); // Atualiza o HUD com o novo número de balas

        }
    }

    /**
     * Reduz a saúde do soldado se ele sofrer dano.
     */
    public void receberDano() {
        if (tempoInvencibilidade == 0) {
            saude -= 10;
            tempoInvencibilidade = TEMPO_MAXIMO_INVENCIBILIDADE;
        }
    }

    /**
     * Verifica se o soldado morreu.
     */
    private void verificarMorte() {
            getSaude();
            int saudeRestante = getSaude();
        if (saude <= 0) {
             Greenfoot.setWorld(new TelaFinal(getSaude()));
        }
    }

    /**
     * Verifica se o soldado colidiu com um pickup de munição.
     */
    private void verificarColisaoComBulletPickup() {
        BulletPickup bulletPickup = (BulletPickup) getOneIntersectingObject(BulletPickup.class);
        if (bulletPickup != null) {
            reloadBullets(5);
            getWorld().removeObject(bulletPickup);
        }
    }

    /**
     * Recarrega a munição do soldado.
     * 
     * @param ammo Quantidade de munição a ser adicionada.
     */
    public void reloadBullets(int ammo) {
        bullets += ammo;
        hud.atualizarBalas(bullets); // Atualiza o HUD com o novo número de balas

    }

    /**
     * Retorna a saúde atual do soldado.
     * 
     * @return Saúde do soldado.
     */
    public int getSaude() {
        return saude;
    }
}
