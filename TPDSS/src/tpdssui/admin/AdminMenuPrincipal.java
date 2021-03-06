package tpdssui.admin;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminMenuPrincipal extends JFrame {
    private JButton adicionarEmpregadoButton;
    private JButton editarEmpregadoButton;
    private JButton removerEmpregadoButton;
    private JButton logOutButton;
    private JPanel rootPanel;
    private JPanel botPanel;
    private JLabel greeting;

    private final ITPDSSLN ln;
    private final String id;

    public AdminMenuPrincipal(ITPDSSLN ln, String id) {
        this.ln = ln;
        this.id = id;

        setGreeting();
        addActions();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ln.save();
            }
        });

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
        try {
            greeting.setText("Bem vindo, " + ln.verEmpregado(id).getNome());
        } catch (EmpregadoNaoExisteException e) {
            new Login(ln);
            dispose();
        }
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
        new Login(ln);
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
        new RemoverEmpregado(ln, id);
    }
}
