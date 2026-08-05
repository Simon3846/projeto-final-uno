package modelo;

import controle.GerenciadorJogo;    

public class CartaCoringa extends Carta{
    
    private String corEscolhida; // A cor escolhida pelo jogador ao jogar a carta coringa

    public CartaCoringa(String valor) {
        super("Coringa", valor);
        this.corEscolhida = null; // Inicialmente, nenhuma cor é escolhida
    }

    public void setCorEscolhida(String novaCor) {
        this.corEscolhida = novaCor;
    }

    @Override
    public String getCor() {
        // Retorna a cor escolhida pelo jogador, se houver
        return (corEscolhida != null) ? corEscolhida : super.getCor();
    }

    @Override
    public void aplicarEfeito(Object jogo){
        GerenciadorJogo gerenciador = (GerenciadorJogo) jogo;

        // Se for uma coringa +4 (ou joker vermelho)
        if(getValor().equals("+4") || getValor().equalsIgnoreCase("Joker Vermelho")){
            gerenciador.forcarCartas(4);

        }

    }
     
}
