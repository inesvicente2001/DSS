package tpdssui.funcionario;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        logoutButton.addActionListener(e -> {
            new Login(ln);
            dispose();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ln.save();
            }
        });

        adicionarPedidoDeOrçamentoButton.addActionListener(e -> new FuncionarioAdicionaPedidoOrcamento(ln,id));

        registarEntregaEPagamentoButton.addActionListener(e -> new FuncionarioRegistoEntregaPagamento(ln, id));


        serviçosExpressoButton.addActionListener(e -> {
            if(ln.getOcupados() < ln.getDisponibilidade()){
                new FuncionarioAdicionaServicoExpresso(ln,id);
            }else{
                showMessageDialog(null, "Não existe mais disponibilidade para Reparações Expresso");
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
