package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jogador {

    private String nome;
    private ArrayList<Carta> mao; // Cartas que o jogador tem na mao

    public Jogador(String nome){
        this.nome = nome;
        this.mao = new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public List<Carta> getMao(){
        return mao;
    }

    // Adicionar uma carta a mao do jogador

    public void adicionarCarta(Carta carta){
        mao.add(carta);
    }

    // Remove a carta da mao do jogador
    public void removerCarta(Carta carta){
        mao.remove(carta);
    }

    public boolean venceu(){
        return mao.isEmpty();
    }
}
