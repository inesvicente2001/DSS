package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioAdicionaPedidoOrcamento extends JFrame{
    private JButton voltarButton;
    private JTextField nomeClienteField;
    private JTextField nifClienteField;
    private JTextField emailClientField;
    private JButton confirmarButton;
    private JLabel nomeClienteLabel;
    private JLabel nifCliente;
    private JLabel emailCliente;
    private JLabel tlmCliente;
    private JTextField tlmClienteField;
    private JLabel nomeEquipamento;
    private JTextField nomeEquipamentoField;
    private JTextField descProblemaField;
    private JLabel descProblema;
    private JLabel prazoMaximo;
    private JTextField diaField;
    private JTextField anoField;
    private JTextField mesField;
    private JLabel sepDiaMes;
    private JLabel sepMesAno;
    private JPanel topPanel;

    private ITPDSSLN ln;

    public FuncionarioAdicionaPedidoOrcamento() {

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

        this.setTitle("Novo Pedido de Orçamento");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    public FuncionarioAdicionaPedidoOrcamento(ITPDSSLN ln){

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
                //Usar metodo da logica de negocio
                //Usando valores obtidos
            }
        });

        this.setTitle("Novo Pedido de Orçamento");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
