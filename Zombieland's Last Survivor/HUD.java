import greenfoot.*;

public class HUD {
    private World world;
    private long startTime;
    private int saude;
    private int bullets = -1; // -1 indica que não está exibindo balas (caso do Tank)

    /**
     * Construtor da classe HUD.
     *
     * @param world     O mundo onde o HUD será exibido.
     * @param startTime O tempo inicial para calcular o tempo de sobrevivência.
     * @param saude     A saúde inicial do jogador.
     */
    public HUD(World world, long startTime, int saude) {
        this.world = world;
        this.startTime = startTime;
        this.saude = saude;
    }

    /**
     * Atualiza a saúde exibida no HUD.
     *
     * @param novaSaude A nova saúde do jogador.
     */
    public void atualizarSaude(int novaSaude) {
        this.saude = novaSaude;
    }

    /**
     * Atualiza o número de balas exibido no HUD.
     *
     * @param bullets O número de balas do jogador.
     */
    public void atualizarBalas(int bullets) {
        this.bullets = bullets;
    }

    /**
     * Exibe as informações na tela.
     * Se bullets for -1, não exibe a contagem de balas (caso do Tank).
     */
    public void exibirInformacoes() {
        long currentTime = System.currentTimeMillis();
        int timeSurvived = (int) ((currentTime - startTime) / 1000); // Tempo de sobrevivência em segundos

        // Exibe o tempo de sobrevivência e a saúde
        world.showText("Time Survived: " + timeSurvived + "s", 100, 60);
        world.showText("Health: " + saude, 100, 20);

        // Exibe as balas apenas se bullets for diferente de -1 (caso do SoldadoSUPERBLAZER)
        if (bullets != -1) {
            world.showText("Bullets: " + bullets, 100, 40);
        }
    }
}