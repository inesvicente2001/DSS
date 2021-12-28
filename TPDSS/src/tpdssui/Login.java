package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.*;
import tpdssui.admin.AdminMenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panel1;
    private JButton logInButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JRadioButton mostrarPasswordRadioButton;

    private ITPDSSLN ln;

    public Login(ITPDSSLN ln) {
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
        Empregado autenticado = ln.verEmpregado(textField1.getText());

        if(autenticado instanceof Administrador) {
            new AdminMenuPrincipal(ln, autenticado);
        } else if(autenticado instanceof Funcionario) {
            // TODO Nova interface funcionário
        } else if(autenticado instanceof Tecnico) {
            // TODO Nova interface técnico
        } else if(autenticado instanceof Gestor) {
            // TODO Nova interface gestor
        }
        dispose();
    }

    private void flipMostrarPassword() {
        passwordField1.setEchoChar(mostrarPasswordRadioButton.isSelected() ? (char) 0 : '*');
    }
}
