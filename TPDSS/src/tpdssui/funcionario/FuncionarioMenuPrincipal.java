package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
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

    private ITPDSSLN ln;

    public FuncionarioMenuPrincipal(ITPDSSLN ln) {
        this.ln = ln;

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
                dispose();
            }
        });

        registarEntregaEPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioRegistoEntregaPagamento(ln);
                dispose();

            }
        });

        serviçosExpressoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioAdicionaServicoExpresso(ln);
                dispose();

            }
        });


        this.setTitle("Funcionário");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public FuncionarioMenuPrincipal() {
        this.ln = new TPDSSLNFacade();

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
                new FuncionarioAdicionaPedidoOrcamento();
                dispose();
            }
        });

        registarEntregaEPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioRegistoEntregaPagamento();
                dispose();

            }
        });

        serviçosExpressoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioAdicionaServicoExpresso();
                dispose();

            }
        });



        this.setTitle("Funcionário");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
