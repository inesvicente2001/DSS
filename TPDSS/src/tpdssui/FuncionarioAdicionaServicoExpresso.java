package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioAdicionaServicoExpresso extends JFrame{
    private JButton voltarButton;
    private JTextField nomeClienteField;
    private JTextField emailClienteField;
    private JButton confirmarButton;
    private JTextField tlmClienteField;
    private JTextField nomeEquipamentoField;
    private JComboBox comboBox1;
    private JTextField nifClienteField;
    private JLabel nomeCliente;
    private JLabel emailCliente;
    private JLabel tlmCliente;
    private JLabel nifCliente;
    private JLabel nomeEquipamento;
    private JPanel topPanel;

    private ITPDSSLN ln;

    public FuncionarioAdicionaServicoExpresso() {

        this.ln = new TPDSSLNFacade();

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioMenuPrincipal();
                dispose();

            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.setTitle("Novo Serviço Expresso");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }


    public FuncionarioAdicionaServicoExpresso(ITPDSSLN ln) {

        this.ln = ln;

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioMenuPrincipal(ln);
                dispose();

            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.setTitle("Novo Serviço Expresso");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

}
