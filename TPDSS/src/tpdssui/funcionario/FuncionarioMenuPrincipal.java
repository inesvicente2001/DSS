package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import static javax.swing.JOptionPane.showMessageDialog;

public class FuncionarioMenuPrincipal extends JFrame{
    private JPanel panel1;
    private JPanel topPanel;
    private JButton adicionarPedidoDeOrçamentoButton;
    private JPanel bottomPanel;
    private JButton logoutButton;
    private JButton registarEntregaEPagamentoButton;
    private JButton serviçosExpressoButton;
    private JLabel welcome;

    private final ITPDSSLN ln;
    private final String id;

    public FuncionarioMenuPrincipal(ITPDSSLN ln, String id) {
        this.ln = ln;
        this.id = id;

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
                new FuncionarioAdicionaPedidoOrcamento(ln,id);
            }
        });

        registarEntregaEPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioRegistoEntregaPagamento(ln, id);

            }
        });


        serviçosExpressoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ln.getOcupados() < ln.getDisponibilidade()){


                    new FuncionarioAdicionaServicoExpresso(ln,id);

                }else{

                    showMessageDialog(null, "Não existe mais disponibilidade para Reparações Expresso");

                }
            }
        });

        try {
            this.welcome.setText("Bem vindo, " + ln.verEmpregado(id).getNome());
        } catch (EmpregadoNaoExisteException e) {
            new Login(ln);
            dispose();
        }
        this.setTitle("Funcionário");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
