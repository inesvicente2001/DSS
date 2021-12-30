package tpdssui.tecnico;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PecasCard extends JPanel {
    private JPanel panel1;
    private JLabel quantidadeLabel;
    private JLabel custoLabel;

    public PecasCard(String peca, float custo, int quantidade) {
        quantidadeLabel.setText(quantidade + " unidades");
        custoLabel.setText(custo + " €");

        this.setLayout(new CardLayout());

        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(BorderFactory.createTitledBorder(b, peca));

        this.add(panel1);
    }
}
