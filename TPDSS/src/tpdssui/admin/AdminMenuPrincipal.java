package tpdssui.admin;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Empregado;
import tpdssui.Login;
import tpdssui.MarcoLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuPrincipal extends JFrame {
    private JButton adicionarEmpregadoButton;
    private JButton editarEmpregadoButton;
    private JButton removerEmpregadoButton;
    private JButton logOutButton;
    private JPanel rootPanel;
    private JPanel botPanel;
    private JLabel greeting;

    private final ITPDSSLN ln;
    private final Empregado autenticado;

    public AdminMenuPrincipal(ITPDSSLN ln, Empregado autenticado) {
        this.ln = ln;
        this.autenticado = autenticado;

        setGreeting();
        addActions();

        // Definir o título
        this.setTitle("Menu Principal");
        // Definir o painel de conteúdo
        this.setContentPane(this.rootPanel);
        // Quando fechamos o menú principal fechamos o programa
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Definir o tamanho e a posição da janela para o meio do ecrã
        this.pack();
        this.setLocationRelativeTo(null);
        // Janela é visível
        this.setVisible(true);
    }

    private void setGreeting() {
        System.out.println(autenticado.getNome());
        greeting.setText("Bem vindo, " + autenticado.getNome());
    }

    // Definir o que cada butão faz quando clicado
    private void addActions() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        adicionarEmpregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEmpregado();
            }
        });
        editarEmpregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEmpregado();
            }
        });
        removerEmpregadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerEmpregado();
            }
        });
    }

    // Para fazer logout, abrir uma nova janela de login e destruir esta janela
    private void logout() {
        new MarcoLogin(ln);
        dispose();
    }

    // Para adicionar um empregado, criar uma janela de adicionar empregado
    private void adicionarEmpregado() {
        new AdicionarEmpregado(ln);
    }

    private void editarEmpregado() {
        new EditarEmpregado(ln);
    }

    // Para remover um empregado, criar uma janela de remoção de empregado
    private void removerEmpregado() {
        new RemoverEmpregado(ln);
    }
}
