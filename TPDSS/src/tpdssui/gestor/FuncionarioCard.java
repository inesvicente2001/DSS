package tpdssui.gestor;

import tpdssln.ssempregados.Funcionario;

import javax.swing.*;
import javax.swing.border.Border;

public class FuncionarioCard extends JPanel {
    private JPanel panel1;
    private JLabel rececoesLabel;
    private JLabel entregasLabel;

    public FuncionarioCard(Funcionario f) {
        rececoesLabel.setText("Receções: " + f.getnRececoes());
        entregasLabel.setText("Entregas: " + f.getnEntregas());

        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(BorderFactory.createTitledBorder(b, f.getNome()));

        this.add(panel1);
    }
}
