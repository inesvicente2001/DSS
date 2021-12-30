package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Tecnico;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TecnicoMenuPrincipal extends JFrame {
    private JButton logoutButton;
    private JButton reparaçõesButton;
    private JButton pedidosDeOrçamentoButton;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel panel1;
    private JLabel welcome;

    private final ITPDSSLN ln;
    private final String id;

    public TecnicoMenuPrincipal(ITPDSSLN ln, String id) {
        this.ln = ln;
        this.id = id;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(ln);
                dispose();
            }
        });

        try {
            this.welcome.setText("Bem vindo, " + ln.verEmpregado(id).getNome());
        } catch (EmpregadoNaoExisteException e) {
            new Login(ln);
            dispose();
        }

        addActions();

        this.setTitle("Técnico");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addActions() {
        pedidosDeOrçamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TecnicoPlanoTrabalho(ln);
            }
        });
        reparaçõesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListReparacoes(ln, id);
            }
        });

    }
}
