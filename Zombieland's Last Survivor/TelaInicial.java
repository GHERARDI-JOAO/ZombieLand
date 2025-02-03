import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta classe cria um menu inicial para o jogador.
 * Inclui botões para iniciar o jogo e acessar informações sobre o jogo.
 * 
 * @author (AJC_GAMES) 
 * @version (07/12/2024)
 */
public class TelaInicial extends MundoPadrao {
    private GreenfootImage backgroundImage; // Imagem de fundo do menu
    private GreenfootSound backgroundMusic; // Música de fundo do menu

    /**
     * Construtor para a TelaInicial.
     * Configura o mundo com a imagem de fundo, música e adiciona os botões.
     */
    public TelaInicial() {
        super(800, 400, 1); // Define o tamanho do mundo

        // Carrega e define a imagem de fundo
        configurarFundo("image.png");

        // Carrega e inicia a música de fundo em loop
        backgroundMusic = new GreenfootSound("musica.mp3");
        backgroundMusic.playLoop();

        // Adiciona os botões "Iniciar" e "Informações"
        adicionarBotoes();
        
        // Adiciona as informações do jogo
        adicionarInformacoes();
    }

    /**
     * Adiciona os botões de "Iniciar" e "Informações" à tela inicial.
     * Os botões são centralizados verticalmente com posições ajustadas horizontalmente.
     */
    private void adicionarBotoes() {
        // Botão "Iniciar"
        GreenfootImage imagemBotaoInicio = new GreenfootImage("start.png"); // Carrega a imagem do botão
        imagemBotaoInicio.scale(200, 80); // Ajusta o tamanho da imagem
        Botao botaoIniciar = new Botao(imagemBotaoInicio, "Iniciar", backgroundMusic); // Cria o botão com a imagem e ação associada
        addObject(botaoIniciar, getWidth() / 5, getHeight() / 2 - 120); // Posiciona o botão na tela

        // Botão "Informações"
        GreenfootImage imagemBotaoInfo = new GreenfootImage("info.png"); // Carrega a imagem do botão
        imagemBotaoInfo.scale(200, 80); // Ajusta o tamanho da imagem
        Botao botaoInformacoes = new Botao(imagemBotaoInfo, "Info", backgroundMusic); // Cria o botão com a imagem e ação associada
        addObject(botaoInformacoes, getWidth() / 5, getHeight() / 2 - 20); // Posiciona o botão na tela
    }

    /**
     * Adiciona as informações do jogo à tela inicial.
     */
    private void adicionarInformacoes() {
        // Título do jogo
        GreenfootImage titulo1 = new GreenfootImage("Zombieland's Last Survivor", 25, Color.WHITE, null);
        getBackground().drawImage(titulo1, getWidth() / 2 - titulo1.getWidth() / 2, 10); // Posiciona o título no topo da tela

        // Informações dos desenvolvedores
        GreenfootImage desenvolvedores = new GreenfootImage("Desenvolvido por: AJC_GAMES", 15, Color.WHITE, null);
        getBackground().drawImage(desenvolvedores, getWidth() / 2 - desenvolvedores.getWidth() / 2, getHeight() - 80); // Posiciona na parte inferior

        // Nome da disciplina
        GreenfootImage nomeDisciplina = new GreenfootImage("Introduçao a Programação Orientada a Objetos", 15, Color.WHITE, null);
        getBackground().drawImage(nomeDisciplina, getWidth() / 2 - nomeDisciplina.getWidth() / 2, getHeight() - 60); // Posiciona abaixo dos desenvolvedores

        // Período
        GreenfootImage periodo = new GreenfootImage("Período: 2024-2", 15, Color.WHITE, null);
        getBackground().drawImage(periodo, getWidth() / 2 - periodo.getWidth() / 2, getHeight() - 40); // Posiciona abaixo da disciplina
    }
}