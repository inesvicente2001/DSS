package tpdssui.tecnico;

import tpdssln.ITPDSSLN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TecnicoPlanoTrabalho extends JFrame{
    private JPanel topPanel;
    private JLabel nomeEquipamento;
    private JLabel idEquipamento;
    private JLabel dataRececao;
    private JLabel descricao;
    private JButton confirmarButton;
    private JButton adicionarPassoButton;
    private JPanel infoOrcamento;
    private JLabel orcamentoAtual;
    private JLabel numLabel;
    private JLabel nomeLabel;
    private JLabel custoTotalLabel;
    private JLabel tempPrevistoLabel;
    private JPanel pecasPanel;
    private JLabel nomePeca;
    private JLabel quantPeca;
    private JLabel custoPeca;
    private JPanel pecaPanel;
    private JPanel peca2Panel;

    private ITPDSSLN ln;

    public TecnicoPlanoTrabalho(ITPDSSLN ln) {

        this.ln = ln;

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.setTitle("Definir PLano de Trabalhos");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
