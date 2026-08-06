package visao;

import javax.swing.*;
import java.awt.*;

class MesaUNO extends JPanel {


    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D) g;


        // fundo da mesa

        g2.setColor(
                new Color(20, 120, 90)
        );

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );


        // linhas decorativas da mesa

        g2.setColor(
                new Color(255,255,255,30)
        );


        for(int i = 0; i < getWidth(); i += 40){

            g2.drawLine(
                    i,
                    0,
                    i,
                    getHeight()
            );

        }


        for(int i = 0; i < getHeight(); i += 40){

            g2.drawLine(
                    0,
                    i,
                    getWidth(),
                    i
            );

        }


    }

}