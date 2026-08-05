package modelo;

import controle.GerenciadorJogo;

//import controle.GerenciadorJogo;

public class CartaAcao extends Carta {

    public CartaAcao(String cor, String valor){
        super(cor,valor); // Valor será "Pular", "Inverter" ou "+2"
    }

    @Override
    public void aplicarEfeito(Object jogo){
        GerenciadorJogo gerenciador = (GerenciadorJogo) jogo;

        switch(getValor()){
            case "Pular":
                gerenciador.pularVez();
                break;

            case "Inverter":
                gerenciador.inverterSentido();
                break;

            case "+2":
                gerenciador.forcarCartas(2);
                break;
        }

        System.out.println("Aplicando o efeito da carta de ação: " + getValor());
    }
}
