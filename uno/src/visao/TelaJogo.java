package visao;

import controle.GerenciadorJogo;
import modelo.Carta;
import modelo.Jogador;

import javax.swing.*;
import java.awt.*;

public class TelaJogo extends JFrame {

    private GerenciadorJogo gerenciador;
    private JLabel lblTurno;
    private JLabel lblCartaTopo;
    private JPanel panelMaoJogador;
    private JButton btnComprar;

    public TelaJogo(GerenciadorJogo gerenciador) {
        this.gerenciador = gerenciador;

        // Configurações da Janela
        setTitle("Partida de UNO");
        setSize(900, 650); // Aumentamos o tamanho para ficar bem espaçoso
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        
        // Fundo geral da janela num tom cinza escuro elegante
        getContentPane().setBackground(new Color(35, 35, 35));

        // 1. Painel Superior: Turno do Jogador
        lblTurno = new JLabel("", SwingConstants.CENTER);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 22));
        lblTurno.setForeground(Color.WHITE);
        lblTurno.setOpaque(true);
        lblTurno.setBackground(new Color(50, 50, 50));
        lblTurno.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTurno, BorderLayout.NORTH);

        // 2. Painel Central: Mesa / Carta do Topo
        JPanel panelMesa = new JPanel(new GridLayout(2, 1, 15, 15));
        panelMesa.setOpaque(false); // Transparente para pegar o fundo escuro

        lblCartaTopo = new JLabel("", SwingConstants.CENTER);
        lblCartaTopo.setFont(new Font("Arial", Font.BOLD, 26));
        lblCartaTopo.setOpaque(true);
        lblCartaTopo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        btnComprar = new JButton("COMPRAR CARTA");
        btnComprar.setFont(new Font("Arial", Font.BOLD, 18));
        btnComprar.setBackground(new Color(230, 230, 230));
        btnComprar.setFocusPainted(false);
        btnComprar.addActionListener(e -> acaoComprarCarta());

        JPanel panelBotaoComprar = new JPanel();
        panelBotaoComprar.setOpaque(false);
        panelBotaoComprar.add(btnComprar);

        panelMesa.add(lblCartaTopo);
        panelMesa.add(panelBotaoComprar);
        add(panelMesa, BorderLayout.CENTER);

        // 3. Painel Inferior: Mão do Jogador
        panelMaoJogador = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelMaoJogador.setBackground(new Color(45, 45, 45));

        JScrollPane scrollMao = new JScrollPane(panelMaoJogador);
        scrollMao.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE), 
            "Sua Mão (Clique na carta para jogar)", 
            0, 0, 
            new Font("Arial", Font.BOLD, 14), 
            Color.WHITE
        ));
        scrollMao.setPreferredSize(new Dimension(880, 200)); // Altura suficiente para exibir as cartas grandes
        scrollMao.setOpaque(false);
        scrollMao.getViewport().setOpaque(false);

        add(scrollMao, BorderLayout.SOUTH);

        atualizarTela();
    }

    private void atualizarTela() {
        Jogador atual = gerenciador.getJogadorAtual();
        Carta topo = gerenciador.getCartaTopo();

        lblTurno.setText("Vez de: " + atual.getNome());

        // Estiliza a carta da Mesa (Topo)
        lblCartaTopo.setText(topo.getValor());
        estilizarElementoPorCor(lblCartaTopo, topo.getCor());

        // Limpa e redesenha a mão
        panelMaoJogador.removeAll();

        for (Carta carta : atual.getMao()) {
            JButton btnCarta = new JButton(carta.getValor());
            btnCarta.setFont(new Font("Arial", Font.BOLD, 18));
            btnCarta.setPreferredSize(new Dimension(100, 130)); // Tamanho proporcional a uma carta real
            btnCarta.setFocusPainted(false);

            // Aplica as cores na carta do jogador
            estilizarElementoPorCor(btnCarta, carta.getCor());

            btnCarta.addActionListener(e -> acaoJogarCarta(carta));
            panelMaoJogador.add(btnCarta);
        }

        panelMaoJogador.revalidate();
        panelMaoJogador.repaint();
    }

    // Método auxiliar para pintar os componentes com as cores das cartas
    private void estilizarElementoPorCor(JComponent componente, String cor) {
        componente.setOpaque(true);

        switch (cor.toLowerCase()) {
            case "vermelho":
            case "copas":
                componente.setBackground(new Color(215, 38, 56)); // Vermelho UNO
                componente.setForeground(Color.WHITE);
                break;
            case "verde":
            case "paus":
                componente.setBackground(new Color(85, 170, 85)); // Verde UNO
                componente.setForeground(Color.WHITE);
                break;
            case "azul":
            case "espadas":
                componente.setBackground(new Color(9, 114, 212)); // Azul UNO
                componente.setForeground(Color.WHITE);
                break;
            case "amarelo":
            case "ouros":
                componente.setBackground(new Color(255, 170, 0)); // Amarelo UNO
                componente.setForeground(Color.BLACK); // Texto escuro para contrastar
                break;
            default: // Caso seja Coringa/Especial
                componente.setBackground(new Color(100, 50, 150));
                componente.setForeground(Color.WHITE);
                break;
        }
    }

    private void acaoJogarCarta(Carta carta) {
    Jogador atual = gerenciador.getJogadorAtual();

    // Se a carta for um Coringa, abre a janela para escolher a cor
    if (carta instanceof modelo.CartaCoringa) {
        String[] opcoesCores;
        
        // Verifica o tipo de baralho na mesa para oferecer os nomes certos (Naipe ou Cor)
        String corTopo = gerenciador.getCartaTopo().getCor();
        if (corTopo.equals("Copas") || corTopo.equals("Ouros") || 
            corTopo.equals("Paus") || corTopo.equals("Espadas")) {
            opcoesCores = new String[]{"Copas", "Ouros", "Paus", "Espadas"};
        } else {
            opcoesCores = new String[]{"Vermelho", "Amarelo", "Verde", "Azul"};
        }

        String corSelecionada = (String) JOptionPane.showInputDialog(
            this,
            "Escolha a nova cor para o jogo:",
            "Seleção de Cor (Coringa)",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoesCores,
            opcoesCores[0]
        );

        // Se o jogador cancelar a escolha, interrompe a jogada
        if (corSelecionada == null) {
            return; 
        }

        ((modelo.CartaCoringa) carta).setCorEscolhida(corSelecionada);
    }

    // Tenta realizar a jogada
    if (gerenciador.jogarCarta(atual, carta)) {
        if (atual.venceu()) {
            JOptionPane.showMessageDialog(this, "Parabéns! " + atual.getNome() + " VENCEU O JOGO!");
            this.dispose();
            return;
        }
        gerenciador.proximoTurno();
        atualizarTela();
    } else {
        JOptionPane.showMessageDialog(this, 
            "Jogada Inválida! A carta precisa ter a mesma cor/naipe ou mesmo valor da mesa.", 
            "Aviso", 
            JOptionPane.WARNING_MESSAGE);
    }
}

    private void acaoComprarCarta() {
        Jogador atual = gerenciador.getJogadorAtual();
        Carta comprada = gerenciador.comprarCarta(atual);

        if (comprada != null) {
            JOptionPane.showMessageDialog(this, atual.getNome() + " comprou: " + comprada.getCor() + " " + comprada.getValor());
            gerenciador.proximoTurno();
            atualizarTela();
        } else {
            JOptionPane.showMessageDialog(this, "O baralho está vazio!");
        }
    }
}