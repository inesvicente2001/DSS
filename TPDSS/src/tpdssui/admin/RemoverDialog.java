package tpdssui.admin;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Empregado;

import javax.swing.*;
import java.awt.event.*;

public class RemoverDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private final Empregado empregado;
    private final ITPDSSLN ln;
    private final JFrame caller;

    public RemoverDialog(ITPDSSLN ln, Empregado empregado, JFrame caller) {
        this.ln = ln;
        this.empregado = empregado;
        this.caller = caller;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
        ln.removerUtilizador(empregado.getId());
        caller.dispose();
        for(Empregado e: ln.acederTecnicos().values()) {
            System.out.println(e.getNome() + "| ID: " + e.getId() + "| Password: " + e.getPassword());
        }
        for(Empregado e: ln.acederFuncionarios().values()) {
            System.out.println(e.getNome() + "| ID: " + e.getId() + "| Password: " + e.getPassword());
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
