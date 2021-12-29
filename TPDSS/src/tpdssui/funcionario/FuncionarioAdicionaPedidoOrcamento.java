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
    private JLabel descProblema;
    private JPanel topPanel;
    private JComboBox urgenciaBox;
    private JLabel urgencia;
    private JTextArea descrProblema;
    private JScrollPane descProblemaScrollPane;

    private ITPDSSLN ln;


    public FuncionarioAdicionaPedidoOrcamento(ITPDSSLN ln){

        this.ln = ln;


        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
