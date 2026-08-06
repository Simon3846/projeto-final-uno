package visao;

import modelo.Carta;

import javax.swing.*;
import java.awt.*;

public class CartaVisual extends JPanel {

    private Carta carta;


    public CartaVisual(Carta carta){

        this.carta = carta;

        setPreferredSize(
                new Dimension(90,130)
        );

        setOpaque(false);

    }


    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);


        Graphics2D g2 = 
                (Graphics2D) g;


        // deixa o desenho mais suave
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );


        // sombra
        g2.setColor(
                new Color(0,0,0,80)
        );

        g2.fillRoundRect(
                8,
                8,
                80,
                120,
                20,
                20
        );


        // corpo da carta

        g2.setColor(
                escolherCor()
        );


        g2.fillRoundRect(
                5,
                5,
                80,
                120,
                20,
                20
        );


        // borda branca

        g2.setColor(Color.WHITE);

        g2.setStroke(
                new BasicStroke(3)
        );


        g2.drawRoundRect(
                5,
                5,
                80,
                120,
                20,
                20
        );


        // valor da carta

        // valor da carta

        g2.setColor(corTexto());


       int tamanhoFonte = 22;

        if (carta.getValor().length() > 2) {
            tamanhoFonte = 14;
        }

        g2.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                tamanhoFonte
            )
        );

        String valor = carta.getValor();


        FontMetrics fm =
                g2.getFontMetrics();


        int x =
                (90 - fm.stringWidth(valor))/2;


        // sombra do texto
    g2.setColor(Color.BLACK);
    g2.drawString(
            valor,
            x + 2,
            82
    );


    // texto principal
    g2.setColor(corTexto());

    g2.drawString(
            valor,
            x,
            80
    );

    }



    private Color escolherCor(){

        if(carta.getCor()==null)
            return Color.BLACK;


        switch(carta.getCor().toLowerCase()){

            case "vermelho":
                return new Color(220, 30, 50);

            case "azul":
                return new Color(20, 90, 200);

            case "verde":
                return new Color(20, 160, 70);

            case "amarelo":
                return new Color(240, 180, 0);

            default:
                return new Color(80,80,80);
            }
    }

    private Color corTexto(){
        return Color.WHITE;
    }
}