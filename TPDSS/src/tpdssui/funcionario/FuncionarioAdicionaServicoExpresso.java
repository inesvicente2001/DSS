package tpdssui.funcionario;

import tpdssln.ITPDSSLN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;

import static javax.swing.JOptionPane.showMessageDialog;

public class FuncionarioAdicionaServicoExpresso extends JFrame{
    private JButton voltarButton;
    private JTextField nomeClienteField;
    private JTextField emailClienteField;
    private JButton confirmarButton;
    private JTextField tlmClienteField;
    private JTextField nomeEquipamentoField;
    private JComboBox servExpressoBox1;
    private JTextField nifClienteField;
    private JLabel nomeCliente;
    private JLabel emailCliente;
    private JLabel tlmCliente;
    private JLabel nifCliente;
    private JLabel nomeEquipamento;
    private JPanel topPanel;
    private JLabel localArmazem;
    private JTextField localArmazemField;

    private ITPDSSLN ln;


    public FuncionarioAdicionaServicoExpresso(ITPDSSLN ln, String idFuncionario) {

        this.ln = ln;

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });


        addActions(idFuncionario);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("Novo Serviço Expresso");
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
                        String emailClienteInput = emailClienteField.getText();
                        String tlmClienteInput = tlmClienteField.getText();
                        String nomeEquipamentoInput = nomeEquipamentoField.getText();
                        String localArmazemInput = localArmazemField.getText();

                        String descEprecoReparacaoInput = servExpressoBox1.getEditor().getItem().toString();

                        String[] descPrecoSeparados = descEprecoReparacaoInput.split("/");


                        ln.adicionarPedidoOrcamentoExpresso(nomeEquipamentoInput,11,"Serviço Expresso: " + descPrecoSeparados[0],
                                localArmazemInput, Long.parseLong(descPrecoSeparados[1]), Duration.ofMinutes(Long.parseLong(descPrecoSeparados[2])),
                                nomeClienteInput,nifClienteInput,tlmClienteInput,emailClienteInput);
                        ln.aumentarRececoesEmpregado(idFuncionario);

                        ln.setOcupados(ln.getOcupados()+1);

                        dispose();


            }
        });

    }

}
