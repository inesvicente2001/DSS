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
    private JLabel statusLabel;

    private final ITPDSSLN ln;
    private final String id;

    public RemoverEmpregado(ITPDSSLN ln, String id) {
        this.ln = ln;
        this.id = id;

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
        String empregado = textField1.getText();
        if(empregado.equals(id)) {
            statusLabel.setText("Não te removas a ti mesmo, por favor");
            statusLabel.setVisible(true);
            this.pack();
        }
        else if(ln.existeEmpregado(empregado)) {
            statusLabel.setVisible(false);
            this.pack();
            new RemoverDialog(ln, empregado, this);
        } else {
            statusLabel.setText("Empregado não existe");
            statusLabel.setVisible(true);
            this.pack();
        }
    }
}
