package modelo;

public class CartaNormal extends Carta{
    
    public CartaNormal(String cor, String valor){
        super(cor, valor);
    }

    @Override
    public void aplicarEfeito(Object jogo){
        // Cartas normais não alteram o fluxo do jogo, apenas são descartadas.
        // O efeito é "vazio".
    }
}
