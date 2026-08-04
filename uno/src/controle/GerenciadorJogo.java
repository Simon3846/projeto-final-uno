package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import modelo.*;

public class GerenciadorJogo {

    private List <Jogador> jogadores;
    private Baralho baralho;
    private Stack<Carta> descarte;
    private int indiceJogadorAtual;
    private boolean sentidoHorario;

    public GerenciadorJogo(List<String> nomesJogadores, Baralho baralhoEscolhido){
       
        this.jogadores = new ArrayList<>();
        for(String nome : nomesJogadores){
            this.jogadores.add(new Jogador(nome));
        }

        this.baralho = baralhoEscolhido;
        this.descarte = new Stack<>();
        this.indiceJogadorAtual = 0;
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

    public Jogador getJogadorAtual(){
        return jogadores.get(indiceJogadorAtual);
    }

    public Carta getCartaTopo(){
        return descarte.peek();
    }

    // passa a vez para o próximo jogador, considerando o sentido do jogo
    public void proximoTurno(){
        int passo = sentidoHorario ? 1 : -1;
        indiceJogadorAtual = (indiceJogadorAtual + passo + jogadores.size()) % jogadores.size();
    }

    // Ação: Tentar jogar uma carta na mão do jogador atual
    public boolean jogarCarta(Jogador jogador, Carta carta) {
        if (!jogador.equals(getJogadorAtual())) {
            return false; // Não é a vez deste jogador
        }

        // Pega a carta que está no topo do descarte (mesa)
        Carta topo = getCartaTopo();

        // Compara a carta da mão COM A CARTA DO TOPO
        if (carta.podeSerJogadaSobre(topo)) {
            jogador.removerCarta(carta);
            descarte.push(carta); // Adiciona a carta ao topo do descarte

            carta.aplicarEfeito(this); // Aplica o efeito da carta (pular, inverter, +2, +4)
            return true;
        }

        return false; // A carta não pode ser jogada
    }

    public Carta comprarCarta(Jogador jogador){
        // Verifica se é a vez do jogador
        if(!jogador.equals(getJogadorAtual())){
            return null;
        }

        Carta NovaCarta = baralho.comprarCarta();
        if(NovaCarta != null){
            jogador.adicionarCarta(NovaCarta);
        }

        return NovaCarta;
    }

    public void pularVez(){
        proximoTurno(); // Pula o jogador atual
    }

    public void inverterSentido(){
        sentidoHorario = !sentidoHorario;
    }

    public void forcarCartas(int quantidade){

        int passo = sentidoHorario ? 1 : -1;
        int proximoIndice = (indiceJogadorAtual + passo + jogadores.size()) % jogadores.size();

        Jogador proximo = jogadores.get(proximoIndice);

        for(int i = 0; i < quantidade; i++){
            Carta c = baralho.comprarCarta();
            if(c != null){
                proximo.adicionarCarta(c);
            }
        }
    }

    public List<Jogador> getJogadores(){
        return jogadores;
    }

}
