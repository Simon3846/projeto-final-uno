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
    private JTextField txtjogador1;
    private JTextField txtjogador2;
    private JRadioButton rdbUnoOficial;
    private JRadioButton rdbConvenional;

    public TelaInicial(){
        // configurações da janela

        setTitle("Uno - Configurações da Partida");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        // Campo de nome
        


    }
    
}
