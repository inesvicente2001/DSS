package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.*;
import tpdssln.ssempregados.excecoes.CredenciaisErradasException;
import tpdssui.admin.AdminMenuPrincipal;
import tpdssui.funcionario.FuncionarioMenuPrincipal;
import tpdssui.gestor.GestorMenuPrincipal;
import tpdssui.tecnico.TecnicoMenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Login extends JFrame {
    private JPanel panel1;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JRadioButton mostrarPasswordRadioButton;
    private JLabel statusLabel;

    private final ITPDSSLN ln;

    public Login(ITPDSSLN ln) {

        // Definir o butão default
        this.getRootPane().setDefaultButton(logInButton);

        this.ln = ln;

        setActions();

        this.setTitle("Login");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setActions() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        mostrarPasswordRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipMostrarPassword();
            }
        });
    }

    private void login() {
        String id = textField1.getText();
        String password = new String(passwordField1.getPassword());
        try {
            Class<? extends Empregado> tipo = ln.autenticar(id, password);

            if (tipo == Administrador.class) {
                new AdminMenuPrincipal(ln, id);
            } else if (tipo == Funcionario.class) {
                new FuncionarioMenuPrincipal(ln, id);
            } else if (tipo == Tecnico.class) {
                new TecnicoMenuPrincipal(ln, id);
            } else if (tipo == Gestor.class) {
                new GestorMenuPrincipal(ln, id);
            }
            dispose();
        } catch (CredenciaisErradasException e) {
            statusLabel.setText("ID ou password inválidos");
            statusLabel.setVisible(true);
            this.pack();
        }
    }


    // Designar o que os butões devem fazer
    private void addKeyStrokes() {
        panel1.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void flipMostrarPassword() {
        passwordField1.setEchoChar(mostrarPasswordRadioButton.isSelected() ? (char) 0 : '*');
    }
}
