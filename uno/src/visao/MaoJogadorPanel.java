package visao;

import modelo.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class MaoJogadorPanel extends JPanel {

    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 150;
    private static final int CARD_GAP = 10;
    private static final int CARD_Y = 15;

    public MaoJogadorPanel() {
        setLayout(null);
        setOpaque(false);
    }

    public void atualizarMao(List<Carta> cartas, Consumer<Carta> aoClicar) {
        removeAll();
        int x = 0;

        for (Carta carta : cartas) {
            CartaVisual visual = new CartaVisual(carta);
            visual.setCardBounds(x, CARD_Y);
            visual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            visual.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 160), 2));
            visual.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (aoClicar != null) {
                        aoClicar.accept(carta);
                    }
                }
            });
            add(visual);
            x += CARD_WIDTH + CARD_GAP;
        }

        int width = Math.max(940, x + 16);
        int height = CARD_HEIGHT + CARD_Y + 10;
        setPreferredSize(new Dimension(width, height));
        revalidate();
        repaint();
    }
}
