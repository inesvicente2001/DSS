package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssui.funcionario.FuncionarioMenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioRegistoEntregaPagamento extends JFrame{
    private JButton voltarButton;
    private JTextField idEquipamentoField;
    private JPanel topPanel;
    private JLabel idEquipamento;
    private JRadioButton pagoRadioButton;
    private JButton confirmarButton;
    private JRadioButton entregueRadioButton;

    private ITPDSSLN ln;

    public FuncionarioRegistoEntregaPagamento(ITPDSSLN ln) {

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

            }
        });

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("Entrega e Pagamento de Equipamento");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

}
