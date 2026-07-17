package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Baralho {

    // Lista protegida para que as subclasses possam nela colocar as cartas
    protected List<Carta> cartas;

    public Baralho(){
        this.cartas = new ArrayList<>();
        inicializarBaralho(); // Chama o método de inicialização específico da subclasse
        embaralhar();
    }

    // Cada baralho concreto vai dizer COMO se cria (se com regras do UNO ou de baralho comum)
    protected abstract void inicializarBaralho();

    // Embaralha as cartas
    public void embaralhar(){
        Collections.shuffle(cartas);
    }

    public Carta comprarCarta(){
        
            if(cartas.isEmpty()){
                // Se o baralho acabar, em um jogo real nós reabasteceríamos com o descarte.
            // Por enquanto, vamos apenas evitar que quebre retornando null.
                return null;
            }

        return cartas.remove(cartas.size() - 1);
    }

    public int quantidadeCartas(){
        return cartas.size();
    }
}
