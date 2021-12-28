package tpdssui.admin;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Empregado;
import tpdssln.ssempregados.Tecnico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverEmpregado extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton confirmarButton;
    private JButton cancelarButton;

    private ITPDSSLN ln;

    public RemoverEmpregado(ITPDSSLN ln) {
        this.ln = ln;

        addActions();

        this.setTitle("Remover Utilizador");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addActions() {
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar();
            }
        });
    }

    private void cancelar() {
        dispose();
    }

    private void confirmar() {
        Empregado empregado = ln.verEmpregado(textField1.getText());
        new RemoverDialog(ln, empregado, this);
    }
}
