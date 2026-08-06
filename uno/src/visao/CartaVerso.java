package visao;

import javax.swing.*;
import java.awt.*;

public class CartaVerso extends JPanel{

    @Override
    protected void paintComponent(Graphics g){

    Graphics2D g2=(Graphics2D)g;

    g2.setColor(Color.BLACK);

    g2.fillRoundRect(
    10,10,
    80,130,
    20,20
    );

    g2.setColor(Color.WHITE);

    g2.drawString(
    "UNO",
    35,
    80
    );

    }

}
