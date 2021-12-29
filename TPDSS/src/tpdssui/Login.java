package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.*;
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

    private ITPDSSLN ln;

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
        Empregado autenticado = ln.verEmpregado(textField1.getText());

        if(autenticado instanceof Administrador) {
            new AdminMenuPrincipal(ln, (Administrador) autenticado);
        } else if(autenticado instanceof Funcionario) {
            new FuncionarioMenuPrincipal(ln, (Funcionario) autenticado);
        } else if(autenticado instanceof Tecnico) {
            new TecnicoMenuPrincipal(ln, (Tecnico) autenticado);
        } else if(autenticado instanceof Gestor) {
            new GestorMenuPrincipal(ln, (Gestor) autenticado);
        }
        dispose();
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
