package modelo;

import controle.GerenciadorJogo;

public class CartaNormal extends Carta{
    
    public CartaNormal(String cor, String valor){
        super(cor, valor);
    }

    @Override
    public void aplicarEfeito(Object jogo){

        GerenciadorJogo gerenciador =
                (GerenciadorJogo) jogo;

        gerenciador.proximoTurno();

    }
}
