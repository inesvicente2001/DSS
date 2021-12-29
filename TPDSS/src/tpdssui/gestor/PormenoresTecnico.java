package tpdssui.gestor;

import tpdssln.ITPDSSLN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PormenoresTecnico extends JFrame{
    private JPanel panel1;
    private JButton voltarButton;

    private ITPDSSLN ln;

    public PormenoresTecnico(ITPDSSLN ln) {

        this.ln = ln;

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        this.setTitle("Lista de Informações Pormenorizadas sobre Técnicos");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
