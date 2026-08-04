package visao;

import controle.GerenciadorJogo;
import modelo.Baralho;
import modelo.BaralhoUnoOficial;
import modelo.BaralhoConvencional;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends JFrame{
    private JTextField txtJogador1;
    private JTextField txtJogador2;
    private JRadioButton rdbUnoOficial;
    private JRadioButton rdbConvencional;

    public TelaInicial(){
        // configurações da janela

        setTitle("Uno - Configurações da Partida");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        // Campo de nome
        JPanel panelJ1 = new JPanel(new FlowLayout());
        panelJ1.add(new JLabel("Jogador 1:"));
        txtJogador1 = new JTextField("Jogador A", 15);
        panelJ1.add(txtJogador1);


        JPanel panelJ2 = new JPanel(new FlowLayout());
        panelJ2.add(new JLabel("Jogador 2:"));
        txtJogador2 = new JTextField("Jogador B", 15);
        panelJ2.add(txtJogador2);

        // opções de baralho
        rdbUnoOficial = new JRadioButton("Uno Oficial", true);
        rdbConvencional = new JRadioButton("Baralho Convencional (52 cartas)");

        ButtonGroup grupoBaralhos = new ButtonGroup();
        grupoBaralhos.add(rdbUnoOficial);
        grupoBaralhos.add(rdbConvencional);

        JPanel panelBaralho = new JPanel();
        panelBaralho.add(rdbUnoOficial);
        panelBaralho.add(rdbConvencional);

        // Botão de iniciar partida
        JButton btnIniciar = new JButton("Iniciar Jogo");
        btnIniciar.addActionListener(e -> iniciarJogo());

        // Adicionando componentes à janela
        add(new JLabel("Bem-vindo ao Uno!", SwingConstants.CENTER));
        add(panelJ1);
        add(panelJ2);
        add(new JLabel("Escolha o tipo de baralho:", SwingConstants.CENTER));
        add(panelBaralho);
        add(btnIniciar);

    }

    private void iniciarJogo(){
        List<String> nomes = new ArrayList<>();
        nomes.add(txtJogador1.getText().trim());
        nomes.add(txtJogador2.getText().trim());

        // Polimorfismo: Escolha do baralho
        Baralho baralhoEscolhido;
        if(rdbUnoOficial.isSelected()){
            baralhoEscolhido = new BaralhoUnoOficial();
        } else {
            baralhoEscolhido = new BaralhoConvencional();
        }

        // Instanciando o controle
        GerenciadorJogo gerenciador = new GerenciadorJogo(nomes, baralhoEscolhido);

        // Fecha esta tela e abre a tela do jogo
        this.dispose();

        // TO DO: TelaJogo telaJogo = new TelaJogo(gerenciador);
        // telaJogo.setVisible(true);

        JOptionPane.showMessageDialog(null, "Jogo iniciado com sucesso! (Tela do jogo ainda não implementada)");

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            TelaInicial tela = new TelaInicial();
            tela.setVisible(true);
        });
    }
}
