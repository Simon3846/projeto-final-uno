package modelo;

public class CartaAcao extends Carta {

    public CartaAcao(String cor, String valor){
        super(cor,valor); // Valor será "Pular", "Inverter" ou "+2"
    }

    @Override
    public void aplicarEfeito(Object jogo){
        // TODO: Quando criarmos a classe do Jogo, vamos programar os efeitos aqui:
        // Se for "Pular" -> jogo.pularVez();
        // Se for "Inverter" -> jogo.inverterSentido();
        // Se for "+2" -> jogo.comprarCartasParaProximo(2);

        System.out.println("Aplicando o efeito da carta de ação: " + getValor());
    }
}
