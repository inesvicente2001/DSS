package tpdssui.tecnico;

import tpdssln.ssreparacoes.Peca;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class AdicionarPeca extends JFrame {
    private JPanel panel1;
    private JTextField nomeField1;
    private JTextField quantidadeField2;
    private JTextField custoField3;
    private JButton confirmarButton;
    private JButton cancelarButton;

    private final Set<Peca> pecas;
    private final AdicionarPasso caller;

    public AdicionarPeca(Set<Peca> pecas, AdicionarPasso caller) {
        this.pecas = pecas;
        this.caller = caller;

        addActions();

        this.setTitle("Criar Peça");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addActions() {
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField1.getText();
                float custo = Float.parseFloat(custoField3.getText());
                int quantidade = Integer.parseInt(quantidadeField2.getText());
                Peca peca = new Peca(nome, custo, quantidade);
                pecas.add(peca);

                PecasCard pc = new PecasCard(peca.getNomePeca(), peca.getCusto(), peca.getQuantidade());
                caller.addPecaCard(pc, peca.getCusto() * peca.getQuantidade());

                dispose();
            }
        });
    }
}
