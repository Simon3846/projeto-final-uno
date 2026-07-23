package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import modelo.*;

public class GerenciadorJogo {

    private List <Jogador> jogadores;
    private Baralho baralho;
    private Stack<Carta> descarte;
    private int jogadorAtualIndex;
    private boolean sentidoHorario;

    public GerenciadorJogo(List<String> nomesJogadores, Baralho baralhoEscolhido){
       
        this.jogadores = new ArrayList<>();
        for(String nome : nomesJogadores){
            this.jogadores.add(new Jogador(nome));
        }

        this.baralho = baralhoEscolhido;
        this.descarte = new Stack<>();
        this.jogadorAtualIndex = 0;
        this.sentidoHorario = true;

        iniciarPartida();
    }

    private void iniciarPartida(){
        // Distribuir 7 cartas para cada jogador
        
        for(int i = 0; i < 7; i++){
            for(Jogador j : jogadores){
                j.adicionarCarta(baralho.comprarCarta());
            }
        }

        // Colocar a primeira carta no descarte
        Carta cartaInicial = baralho.comprarCarta();
        descarte.push(cartaInicial);
    }

}