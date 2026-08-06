package visao;

import controle.GerenciadorJogo;
import modelo.Baralho;
import modelo.BaralhoUnoOficial;
import modelo.BaralhoConvencional;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class TelaInicial extends JFrame {

    private JPanel painelConfiguracoes;
    private JRadioButton rdbUnoOficial;
    private JRadioButton rdbConvencional;
    private JComboBox<Integer> cmbQuantidadeJogadores;
    private JPanel painelJogadores;
    private List<JTextField> camposJogadores;
    private JPanel painelCentral;
    private JPanel painelFundo;


    public TelaInicial() {
        configurarJanela();
        criarTitulo();
        criarPainelConfiguracoes();
        criarOpcaoBaralho();
        criarQuantidadeJogadores();
        criarCamposJogadores();
        criarBotaoIniciar();

        atualizarCamposJogadores();
    }

    private void configurarJanela() {
        setTitle("UNO");
        setSize(900, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        painelFundo = new JPanel(new BorderLayout());
        painelFundo.setBackground(new Color(30, 30, 30));
        setContentPane(painelFundo);
    }

    private void criarTitulo(){
        JLabel lblTitulo = new JLabel("UNO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));

        lblTitulo.setBorder(
            BorderFactory.createEmptyBorder(20, 0, 20, 0));

        add(lblTitulo, BorderLayout.NORTH);

    }

    private void criarPainelConfiguracoes(){

        painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setOpaque(false);

        painelConfiguracoes = new JPanel();

        painelConfiguracoes.setLayout(
                new BoxLayout(painelConfiguracoes, BoxLayout.Y_AXIS));

       painelConfiguracoes.setPreferredSize(new Dimension(500, 550));

        painelConfiguracoes.setBackground(Color.WHITE);
        painelConfiguracoes.setOpaque(true);

        painelConfiguracoes.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        painelCentral.add(painelConfiguracoes, gbc);

        add(painelCentral, BorderLayout.CENTER);
    }

    private void criarOpcaoBaralho() {

        JPanel painelBaralho = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblBaralho = new JLabel("Tipo de Baralho:");
        lblBaralho.setFont(new Font("Arial", Font.BOLD, 16));

        rdbUnoOficial = new JRadioButton("UNO Oficial", true);
        rdbConvencional = new JRadioButton("Baralho Convencional");

        rdbUnoOficial.setFont(new Font("Arial", Font.PLAIN, 14));
        rdbConvencional.setFont(new Font("Arial", Font.PLAIN, 14));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbUnoOficial);
        grupo.add(rdbConvencional);

        painelBaralho.add(lblBaralho);
        painelBaralho.add(rdbUnoOficial);
        painelBaralho.add(rdbConvencional);

        painelConfiguracoes.add(painelBaralho);
        painelConfiguracoes.add(Box.createVerticalStrut(15)); // Espaçamento entre os componentes
    }

    private void criarQuantidadeJogadores(){
        
        JPanel painelQuantidade = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblQuantidade = new JLabel("Quantidade de jogadores:");

        lblQuantidade.setFont(new Font("Arial", Font.BOLD, 16));

        Integer[] quantidade = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        cmbQuantidadeJogadores = new JComboBox<>(quantidade);

        cmbQuantidadeJogadores.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbQuantidadeJogadores.setPreferredSize(new Dimension(60, 30));

        painelQuantidade.add(lblQuantidade);
        painelQuantidade.add(cmbQuantidadeJogadores);

        painelConfiguracoes.add(painelQuantidade);
        painelConfiguracoes.add(Box.createVerticalStrut(15));

        cmbQuantidadeJogadores.addActionListener(e -> atualizarCamposJogadores());
    }

    private void criarCamposJogadores(){
        
        painelJogadores = new JPanel();
        painelJogadores.setLayout(new BoxLayout(painelJogadores, BoxLayout.Y_AXIS));

        camposJogadores = new ArrayList<>();

        painelConfiguracoes.add(painelJogadores);
        painelConfiguracoes.add(Box.createVerticalStrut(20));

    }

    private void atualizarCamposJogadores() {

        painelJogadores.removeAll();
        camposJogadores.clear();

        int quantidade = (Integer) cmbQuantidadeJogadores.getSelectedItem();

        for (int i = 1; i <= quantidade; i++) {

            JPanel linha = new JPanel(new FlowLayout(FlowLayout.CENTER));

            JLabel lbl = new JLabel("Jogador " + i + ": ");
            lbl.setFont(new Font("Arial", Font.PLAIN, 14));

            JTextField txtNome = new JTextField(15);
            txtNome.setText("Jogador " + i);

            camposJogadores.add(txtNome);

            linha.add(lbl);
            linha.add(txtNome);

            painelJogadores.add(linha);
        }

        painelJogadores.revalidate();
        painelJogadores.repaint();
    }

    private void criarBotaoIniciar(){
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnIniciar = new JButton("Iniciar Jogo");
        btnIniciar.setPreferredSize(new Dimension(180, 40));
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));

        btnIniciar.addActionListener(e -> iniciarJogo());

        painelBotao.add(btnIniciar);

        painelConfiguracoes.add(Box.createVerticalStrut(15));
        painelConfiguracoes.add(painelBotao);
    }

    private void iniciarJogo(){
        
        List<String> nomes = new ArrayList<>();

        for (JTextField campo : camposJogadores) {
                nomes.add(campo.getText().trim());
            }

        Baralho baralho;

        if(rdbUnoOficial.isSelected()){
            baralho = new BaralhoUnoOficial();
        } else {
            baralho = new BaralhoConvencional();
        }

        GerenciadorJogo gerenciador = new GerenciadorJogo(nomes, baralho);

        dispose();

        TelaJogo telaJogo = new TelaJogo(gerenciador);
        telaJogo.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }

}
