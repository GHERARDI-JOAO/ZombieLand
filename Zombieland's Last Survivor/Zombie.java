import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
// Superclass Zombie
public abstract class Zombie extends Actor {
    // Protected fields to be accessible by subclasses
    protected GreenfootImage[] idleDireita;
    protected GreenfootImage[] idleEsquerda;
    protected GreenfootImage[] runDireita;
    protected GreenfootImage[] runEsquerda;
    protected int indiceImagemAtual;
    protected int passosParaAtualizarAImagem;
    protected int passosDesdeUltimaAtualizacaoImagem;
    protected int velocidade; // New field for movement speed

    public Zombie() {
        passosParaAtualizarAImagem = 5;
        passosDesdeUltimaAtualizacaoImagem = 0;
        
        // Load base animations
        idleDireita = carregarImagens("skeleton-idle_", 16, false);
        idleEsquerda = carregarImagens("skeleton-idle_", 16, true);
        runDireita = carregarImagens("skeleton-move_", 17, false);
        runEsquerda = carregarImagens("skeleton-move_", 17, true);

        indiceImagemAtual = 0;
        setImage(idleDireita[indiceImagemAtual]);
    }

    public void act() {
        perseguirJogador();
        atualizarAnimacao();
        verificarColisaoComJogador();

        Bullet bullet = (Bullet) getOneIntersectingObject(Bullet.class);
        if (bullet != null) {
            getWorld().removeObject(bullet);
            getWorld().removeObject(this);
        }
        
        if (getWorld() instanceof MyWorld) {
            ((MyWorld) getWorld()).removerZumbi(this);
        } else if (getWorld() instanceof SecondPhase) {
            ((SecondPhase) getWorld()).removerZumbi(this);
        }
    }

    protected void perseguirJogador() {
        Actor jogador = getClosestTankOrSoldier();
        if (jogador != null) {
            turnTowards(jogador.getX(), jogador.getY());
            move(velocidade); // Use the speed field instead of hardcoded value
        }
    }

    // Other existing methods remain the same but changed to protected
    protected Actor getClosestTankOrSoldier() {
        // Existing implementation
        Actor closest = null;
        double closestDistance = Double.MAX_VALUE;

        java.util.List<Actor> atores = getWorld().getObjects(Actor.class);
        
        for (Actor ator : atores) {
            if (ator instanceof Tank || ator instanceof SoldadoSUPERBLAZER) {
                double distance = Math.sqrt(Math.pow(getX() - ator.getX(), 2) + Math.pow(getY() - ator.getY(), 2));
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closest = ator;
                }
            }
        }
        return closest;
    }

    protected void atualizarAnimacao() {
        // Existing implementation
        passosDesdeUltimaAtualizacaoImagem++;
        if (passosDesdeUltimaAtualizacaoImagem >= passosParaAtualizarAImagem) {
            passosDesdeUltimaAtualizacaoImagem = 0;
            indiceImagemAtual = (indiceImagemAtual + 1) % 16;
            setImage(runDireita[indiceImagemAtual]);
        }
    }

    protected void verificarColisaoComJogador() {
        // Existing implementation
        Actor jogador = getClosestTankOrSoldier();
        if (jogador != null) {
            int distanciaX = getX() - jogador.getX();
            int distanciaY = getY() - jogador.getY();
            double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

            if (distancia < 50) {
                if (jogador instanceof SoldadoSUPERBLAZER) {
                    ((SoldadoSUPERBLAZER) jogador).receberDano();
                } else if (jogador instanceof Tank) {
                    ((Tank) jogador).receberDano();
                }
            }
        }
    }

    protected GreenfootImage[] carregarImagens(String prefixo, int quantidade, boolean espelhar) {
        // Existing implementation
        GreenfootImage[] imagens = new GreenfootImage[quantidade];
        for (int i = 0; i < quantidade; i++) {
            imagens[i] = new GreenfootImage(prefixo + i + ".png");
            imagens[i].scale(120, 140);
            if (espelhar) {
                imagens[i].mirrorHorizontally();
            }
        }
        return imagens;
    }
}