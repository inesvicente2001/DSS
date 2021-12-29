package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Funcionario;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioMenuPrincipal extends JFrame{
    private JPanel panel1;
    private JPanel topPanel;
    private JButton adicionarPedidoDeOrçamentoButton;
    private JPanel bottomPanel;
    private JButton logoutButton;
    private JButton registarEntregaEPagamentoButton;
    private JButton serviçosExpressoButton;
    private JLabel welcome;

    private ITPDSSLN ln;
    private Funcionario funcionario;

    public FuncionarioMenuPrincipal(ITPDSSLN ln, Funcionario autenticado) {
        this.ln = ln;
        this.funcionario = autenticado;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(ln);
                dispose();
            }
        });

        adicionarPedidoDeOrçamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioAdicionaPedidoOrcamento(ln);
            }
        });

        registarEntregaEPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioRegistoEntregaPagamento(ln);

            }
        });

        serviçosExpressoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioAdicionaServicoExpresso(ln);
            }
        });

        this.welcome.setText("Bem vindo, " + funcionario.getNome());
        this.setTitle("Funcionário");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
