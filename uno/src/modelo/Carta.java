
// BESEADO NO MODELO MVC (MODEL-VIEW-CONTROLLER)

/*
    Modelo: sera aqui onde vai ficar a logica do jogo, cartas, baralhos, jogadores
*/

package modelo;


// Abstract representa um modelo

public abstract class Carta {
    private String cor; // Representar as cores
    private String valor; // Representar os numeros


    // construtor: inicializa a classe carta 
    public Carta(String cor, String valor){
        this.cor = cor;
        this.valor = valor;
    }

    public String getCor(){
        return cor;
    }

    public String getValor(){
        return valor;
    }

    // Metodo abstrato
    public abstract void aplicarEfeito(Object jogo);

    //Metodo pra verificar se a carta pode ser jogada por cima
    public boolean podeSerJogadaSobre(Carta outra){
        // Um coringa sempre pode ser jogado, ou se tiver a mesma cor, ou mesmo valor
        return this.cor.equals("Especial") ||
        outra.getCor().equals("Especial") ||
        this.cor.equals(outra.getCor()) ||
        this.valor.equals(outra.getValor());

    }
@Override
    // define como a carta sera imprensa
    public String toString(){
        return  cor + " " + valor;
    } 
        

}
