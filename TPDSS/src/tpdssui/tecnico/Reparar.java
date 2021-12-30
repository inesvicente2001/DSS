package tpdssui.tecnico;

import tpdssln.ITPDSSLN;

import javax.swing.*;

public class Reparar extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton próximoPassoButton;
    private JButton suspenderButton;

    private ITPDSSLN ln;
    private String id;

    public Reparar(ITPDSSLN ln, String id) {
        this.ln = ln;
        this.id = id;

        this.setTitle("Reparar equipamento");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
