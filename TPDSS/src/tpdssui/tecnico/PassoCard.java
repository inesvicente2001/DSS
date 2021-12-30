package tpdssui.tecnico;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PassoCard extends JPanel {
    private JPanel panel1;
    private JLabel nomeLabel;
    private JLabel custoLabel;
    private JLabel tempoLabel;
    private JPanel pecasPanel;

    public PassoCard(String id, String nome, String custo, String tempo) {
        nomeLabel.setText(nome);
        custoLabel.setText(custo);
        tempoLabel.setText(tempo);

        //\this.setLayout(new GridLayout(1,1));
        this.setLayout(new CardLayout());
        pecasPanel.setLayout(new BoxLayout(pecasPanel, BoxLayout.Y_AXIS));


        PecasCard p = new PecasCard("Parafuso", 12, 13);
        pecasPanel.add(p);

        PecasCard p2 = new PecasCard("ola", 99, 690);
        pecasPanel.add(p2);
        PecasCard p3 = new PecasCard("123", 987, 6430);
        pecasPanel.add(p3);

        this.setPreferredSize(panel1.getPreferredSize());
        this.add(panel1);

        Border lbb = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createTitledBorder(lbb, "Passo " + id));
    }

    public void addPeca(JPanel peca) {
        this.pecasPanel.add(peca);
    }
}
