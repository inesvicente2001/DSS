package tpdssui.gestor;

import javax.swing.*;
import javax.swing.border.Border;

public class FuncionarioCard extends JPanel {
    private JPanel panel1;
    private JLabel rececoesLabel;
    private JLabel entregasLabel;

    public FuncionarioCard(String nome, int rececoes, int entregas) {
        rececoesLabel.setText("Receções: " + rececoes);
        entregasLabel.setText("Entregas: " + entregas);

        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(BorderFactory.createTitledBorder(b, nome));

        this.add(panel1);
    }
}
