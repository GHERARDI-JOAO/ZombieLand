import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe Botao representa botões interativos na interface do jogo.
 * Os botões podem realizar ações como iniciar o jogo, exibir informações ou retornar ao menu inicial.
 */
public class Botao extends Actor {

    private GreenfootImage imagemBotao; // Imagem exibida no botão
    private String acao; // Ação associada ao botão (ex.: "Iniciar", "Info", "Voltar")
    private GreenfootSound backgroundMusic; // Música de fundo a ser controlada

    /**
     * Construtor da classe Botao.
     * 
     * @param imagem Imagem que será exibida no botão.
     * @param acao Ação associada ao botão (Iniciar, Info, Voltar).
     * @param musica Música de fundo que será tocada ou parada ao clicar no botão.
     */
    public Botao(GreenfootImage imagem, String acao, GreenfootSound musica) {
        this.imagemBotao = imagem; // Define a imagem do botão
        this.acao = acao; // Define a ação do botão
        this.backgroundMusic = musica; // Define a música de fundo associada ao botão
        setImage(imagemBotao); // Configura a imagem do botão como a imagem do ator
    }

    /**
     * Método chamado a cada ciclo do mundo.
     * Verifica se o botão foi clicado e executa a ação associada.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) { // Verifica se o botão foi clicado
            executarAcao(); // Executa a ação correspondente
        }
    }

    /**
     * Executa a ação associada ao botão com base na variável 'acao'.
     * Troca o mundo atual ou realiza outra lógica conforme necessário.
     */
    private void executarAcao() {
        // Troca para o mundo do jogo
        if (acao.equals("Iniciar")) {
            Greenfoot.setWorld(new MyWorld()); // Vai para o mundo do jogo
            if (backgroundMusic != null) {
                backgroundMusic.stop(); // Para a música de fundo, se existir
            }
        }
        // Troca para o mundo de informações
        else if (acao.equals("Info")) {
            Greenfoot.setWorld(new MundoInformacoes()); // Vai para o mundo de informações
        }
        // Retorna para a tela inicial
        else if (acao.equals("Voltar")) {
            Greenfoot.setWorld(new TelaInicial()); // Vai para a tela inicial
        }
    }
}
