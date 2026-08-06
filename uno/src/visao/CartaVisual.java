package visao;

import modelo.Carta;

import javax.swing.*;
import java.awt.*;

public class CartaVisual extends JPanel {

    private static final int CARD_WIDTH = 90;
    private static final int CARD_HEIGHT = 130;

    private Carta carta;


    public CartaVisual(Carta carta){

        this.carta = carta;

        setPreferredSize(
                new Dimension(CARD_WIDTH, CARD_HEIGHT)
        );

        setOpaque(false);

    }


    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        // suavização
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
                CARD_WIDTH - 10,
                CARD_HEIGHT - 10,
                20,
                20
        );


        // fundo da carta

        g2.setColor(
                escolherCor()
        );


        g2.fillRoundRect(
                5,
                5,
                CARD_WIDTH - 10,
                CARD_HEIGHT - 10,
                20,
                20
        );


        // borda

        g2.setColor(
                corBorda()
        );

        g2.setStroke(
                new BasicStroke(3)
        );


        g2.drawRoundRect(
                5,
                5,
                CARD_WIDTH - 10,
                CARD_HEIGHT - 10,
                20,
                20
        );


        // Se for baralho convencional desenha naipe

        if(isBaralhoNormal()){

            String naipe = carta.getCor();


            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );


            g2.setColor(
                    corTexto()
            );


            int largura =
                    g2.getFontMetrics()
                            .stringWidth(naipe);


            g2.drawString(
                    naipe,
                    (CARD_WIDTH - largura)/2,
                    25
            );


            // símbolo

            g2.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            28
                    )
            );


            g2.drawString(
                    simboloNaipe(),
                    34,
                    55
            );

        }



        // valor da carta

        String valor = carta.getValor();


        if(valor == null){
            valor = "";
        }


        int tamanhoFonte = 22;

        int tamanho = carta.getValor().length();

        if (tamanho > 10) {
            tamanhoFonte = 10;
        } 
        else if (tamanho > 5) {
            tamanhoFonte = 14;
        }


        g2.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        tamanhoFonte
                )
        );


        FontMetrics fm =
                g2.getFontMetrics();


        int x =
                (CARD_WIDTH - fm.stringWidth(valor))/2;



        // sombra do texto

        g2.setColor(Color.BLACK);


        g2.drawString(
                valor,
                x + 2,
                95
        );



        // texto principal

        g2.setColor(
                corTexto()
        );


        g2.drawString(
                valor,
                x,
                93
        );

    }



    private boolean isBaralhoNormal(){

        if(carta.getCor() == null)
            return false;


        String cor =
                carta.getCor().toLowerCase();


        return cor.equals("copas")
                || cor.equals("ouros")
                || cor.equals("paus")
                || cor.equals("espadas");
    }



    private String simboloNaipe(){

        switch(carta.getCor().toLowerCase()){

            case "copas":
                return "♥";

            case "ouros":
                return "♦";

            case "paus":
                return "♣";

            case "espadas":
                return "♠";

            default:
                return "";
        }
    }



    private Color escolherCor(){

        if(carta.getCor()==null)
            return Color.BLACK;



        switch(carta.getCor().toLowerCase()){


            // UNO

            case "vermelho":
                return new Color(220,30,50);


            case "azul":
                return new Color(20,90,200);


            case "verde":
                return new Color(20,160,70);


            case "amarelo":
                return new Color(240,180,0);



            // baralho normal

            case "copas":
            case "ouros":
            case "paus":
            case "espadas":
                return Color.WHITE;


            default:
                return Color.BLACK;
        }

    }



    private Color corTexto(){

        if(carta.getCor()==null)
            return Color.WHITE;


        switch(carta.getCor().toLowerCase()){


            // baralho normal

            case "copas":
            case "ouros":
                return Color.RED;


            case "paus":
            case "espadas":
                return Color.BLACK;



            // UNO

            default:
                return Color.WHITE;
        }

    }



    private Color corBorda(){

        if(isBaralhoNormal())
            return Color.BLACK;


        return Color.WHITE;
    }



    public void setCardBounds(int x, int y){

        setBounds(
                x,
                y,
                CARD_WIDTH,
                CARD_HEIGHT
        );
    }
}