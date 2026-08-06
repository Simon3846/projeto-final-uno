package visao;

import controle.GerenciadorJogo;
import modelo.Carta;
import modelo.CartaCoringa;
import modelo.Jogador;

import javax.swing.*;
import java.awt.*;

public class TelaJogo extends JFrame {

    private JPanel panelMesa;
    private GerenciadorJogo gerenciador;
    private JLabel lblTurno;
    private CartaVisual cartaTopoVisual;
    private MaoJogadorPanel panelMaoJogador;
    private JButton btnComprar;
    private JButton btnPassar;


    public TelaJogo(GerenciadorJogo gerenciador){

        this.gerenciador = gerenciador;


        setTitle("Partida de UNO");
        setSize(900,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(15,15));

        getContentPane()
                .setBackground(new Color(35,35,35));



        // TOPO

        lblTurno = new JLabel(
                "",
                SwingConstants.CENTER
        );

        lblTurno.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        lblTurno.setForeground(Color.WHITE);

        lblTurno.setOpaque(true);

        lblTurno.setBackground(
                new Color(50,50,50)
        );

        add(
                lblTurno,
                BorderLayout.NORTH
        );



        // MESA

        panelMesa = new JPanel();

        panelMesa.setLayout(
                new BoxLayout(
                        panelMesa,
                        BoxLayout.Y_AXIS
                )
        );

        panelMesa.setOpaque(false);



        cartaTopoVisual =
                new CartaVisual(
                        gerenciador.getCartaTopo()
                );

        cartaTopoVisual.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );



         btnComprar = new JButton("COMPRAR CARTA");

        btnComprar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        btnComprar.addActionListener(
                e -> acaoComprarCarta()
        );


        btnPassar = new JButton("PASSAR TURNO");

        btnPassar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        btnPassar.addActionListener(
                e -> acaoPassarTurno()
        );


        panelMesa.add(
                cartaTopoVisual
        );


        panelMesa.add(
                Box.createVerticalStrut(20)
        );


        JPanel painelBotoes = new JPanel();

        painelBotoes.setOpaque(false);

        painelBotoes.add(btnComprar);
        painelBotoes.add(btnPassar);


panelMesa.add(painelBotoes);


        add(
                panelMesa,
                BorderLayout.CENTER
        );



        // MÃO

        panelMaoJogador =
                new MaoJogadorPanel();


        JScrollPane scroll =
                new JScrollPane(
                        panelMaoJogador
                );


        scroll.setPreferredSize(
                new Dimension(
                        880,
                        200
                )
        );


        add(
                scroll,
                BorderLayout.SOUTH
        );


        atualizarTela();

    }




    private void atualizarTela(){


        Jogador atual =
                gerenciador.getJogadorAtual();



        lblTurno.setText(
                "Vez de: "
                + atual.getNome()
        );



        panelMesa.remove(
                cartaTopoVisual
        );


        cartaTopoVisual =
                new CartaVisual(
                        gerenciador.getCartaTopo()
                );


        cartaTopoVisual.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        panelMesa.add(
                cartaTopoVisual,
                0
        );



        panelMaoJogador.atualizarMao(
                atual.getMao(),
                this::acaoJogarCarta
        );


        panelMesa.revalidate();
        panelMesa.repaint();

    }




    private void acaoJogarCarta(Carta carta){


        Jogador atual =
                gerenciador.getJogadorAtual();



        // CORINGA

        if(carta instanceof CartaCoringa){


            String corTopo =
                    gerenciador
                    .getCartaTopo()
                    .getCor();



            String[] cores;



            if(
                "Copas".equalsIgnoreCase(corTopo)
                ||
                "Ouros".equalsIgnoreCase(corTopo)
                ||
                "Paus".equalsIgnoreCase(corTopo)
                ||
                "Espadas".equalsIgnoreCase(corTopo)
            ){

                cores = new String[]{
                        "Copas",
                        "Ouros",
                        "Paus",
                        "Espadas"
                };


            }else{


                cores = new String[]{
                        "Vermelho",
                        "Amarelo",
                        "Verde",
                        "Azul"
                };

            }



            String escolhida =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Escolha a cor:",
                            "Coringa",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            cores,
                            cores[0]
                    );



            if(escolhida == null)
                return;



            ((CartaCoringa)carta)
                    .setCorEscolhida(escolhida);

        }



        if(
    gerenciador.jogarCarta(
        atual,
        carta
    )
){

    if(atual.venceu()){

        JOptionPane.showMessageDialog(
                this,
                atual.getNome()+" venceu!"
        );

        dispose();
        return;
    }


    atualizarTela();

        }else{


            JOptionPane.showMessageDialog(
                    this,
                    "Carta inválida!"
            );

        }

    }




    private void acaoComprarCarta(){


        Jogador atual =
                gerenciador.getJogadorAtual();



        Carta comprada =
                gerenciador.comprarCarta(atual);



        if(comprada == null){


            JOptionPane.showMessageDialog(
                    this,
                    "Baralho vazio!"
            );

            return;

        }



        int resposta =
                JOptionPane.showConfirmDialog(
                        this,
                        "Carta comprada: "
                        +
                        comprada
                        +
                        "\nDeseja jogar?",
                        "Comprar",
                        JOptionPane.YES_NO_OPTION
                );



        if(resposta == JOptionPane.YES_OPTION){


            if(
                gerenciador.jogarCarta(
                        atual,
                        comprada
                )
            ){

                gerenciador.proximoTurno();

            }


        }else{


            gerenciador.proximoTurno();

        }



        atualizarTela();

    }

    private void acaoPassarTurno(){

    gerenciador.proximoTurno();

    atualizarTela();

    }

}