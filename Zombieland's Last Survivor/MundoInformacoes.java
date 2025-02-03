import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Mundo que exibe as informações sobre o jogo.
 */
public class MundoInformacoes extends MundoPadrao {

    /**
     * Construtor para o mundo de informações.
     */
    public MundoInformacoes() {
        super(1920, 1080, 1); // Define o tamanho do mundo

        // Configura o fundo preto
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        configurarFundo(Color.BLACK); // Define a cor do fundo como preto
       

        // Define o texto informativo
        String textoInfo = "Bem-vindo ao ZombieLand's Last Survivor\n" +
                   "Aqui, você está cercado de zumbis e precisa sobreviver ao menos 2 minutos, até que chegue a ajuda.\n" +
                   "Instruções: \n" +
                   "Você tem inicialmente apenas 10 balas em seu pente.\n" + 
                   "De tempos em tempos spawnam munições no chão (é de alta sagacidade pegá-las).\n" +
                   "A cada 30 segundos começam a spawnar mais zumbis, então fique esperto.\n\n" +
                   "Fase 2:\n" +
                   "Após sobreviver à primeira onda, você acaba ficando preso dentro de um tanque estragado que não se move.\n" +
                   "Dessa vez, você terá munição infinita, porém os zumbis estarão mais rápidos e irão cercá-lo.\n" +
                   "Sobreviva por mais 2 minutos até que o resgate finalmente chegue!\n\n" +
                   "Pressione a tecla V ou o botão voltar para retornar à tela inicial.";
        // Cria uma imagem com o texto e configurações de fonte e cor
        GreenfootImage texto = new GreenfootImage(textoInfo, 24, Color.WHITE, new Color(0, 0, 0, 0));

        // Calcula a posição para centralizar o texto
        int textWidth = texto.getWidth();
        int textHeight = texto.getHeight();
        int xPos = (getWidth() - textWidth) / 2;
        int yPos = 100;

        // Desenha o texto no fundo
        getBackground().drawImage(texto, xPos, yPos);

        // Adiciona um botão para voltar ao menu inicial
        GreenfootImage imagemBotaoVoltar = new GreenfootImage("voltar.png"); // Carrega a imagem do botão
        imagemBotaoVoltar.scale(150, 50); // Ajusta o tamanho da imagem
        Botao botaoVoltar = new Botao(imagemBotaoVoltar, "Voltar", null); // Cria o botão com a imagem
        addObject(botaoVoltar, getWidth() / 2, getHeight() - 180); // Posiciona o botão no mundo
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("v")) {
            Greenfoot.setWorld(new TelaInicial()); // Reinicia o jogo
        }
    }
}
