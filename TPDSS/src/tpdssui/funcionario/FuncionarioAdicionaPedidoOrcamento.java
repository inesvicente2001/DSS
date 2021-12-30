package tpdssui.funcionario;

import tpdssln.ITPDSSLN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

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
    private JTextField localProblemaField;

    private ITPDSSLN ln;


    public FuncionarioAdicionaPedidoOrcamento(ITPDSSLN ln, String idFuncionario){

        this.ln = ln;


        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        addActions(idFuncionario);



        this.setTitle("Novo Pedido de Orçamento");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void addActions(String idFuncionario) {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeClienteInput = nomeClienteField.getText();
                String nifClienteInput = nifClienteField.getText();
                String emailClienteInput = emailClientField.getText();
                String tlmClienteInput = tlmClienteField.getText();
                String nomeEquipamentoInput = nomeEquipamentoField.getText();
                String localArmazemInput = localProblemaField.getText();
                String descricaoInput = descrProblema.getText();

                int urgencia = Integer.parseInt(urgenciaBox.getEditor().getItem().toString());


                ln.adicionarPedidoOrcamentoNormal(nomeEquipamentoInput,urgencia,descricaoInput,localArmazemInput,
                        nomeClienteInput,nifClienteInput,tlmClienteInput,emailClienteInput);
                ln.aumentarRececoesEmpregado(idFuncionario);

                dispose();
            }
        });

    }

}
