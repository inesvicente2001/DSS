package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Peca;
import tpdssui.funcionario.FuncionarioMenuPrincipal;
import tpdssui.tecnico.NovoPassosCard;
import tpdssui.tecnico.PecasCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

public class FuncionarioRegistoEntregaPagamento extends JFrame{
    private JButton voltarButton;
    private JTextField idEquipamentoField;
    private JPanel topPanel;
    private JLabel idEquipamento;
    private JRadioButton pagoRadioButton;
    private JButton confirmarButton;
    private JRadioButton entregueRadioButton;

    private ITPDSSLN ln;

    public FuncionarioRegistoEntregaPagamento(ITPDSSLN ln, String idFuncionario) {

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


        addActions(idFuncionario);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("Entrega e Pagamento de Equipamento");
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
                if (entregueRadioButton.isSelected() && pagoRadioButton.isSelected()) {
                    String idEquipamentoInput = idEquipamentoField.getText();

                    ln.registarEntrega(idEquipamentoInput);
                    ln.aumentarEntregasEmpregado(idFuncionario);

                    dispose();
                }
            }
        });

    }

}
