package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Tecnico;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Peca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reparar extends JFrame {
    private JPanel panel1;
    private JButton próximoPassoButton;
    private JButton suspenderButton;
    private JScrollPane scrollPane;
    private JTextField textField1;
    private JPanel passoPanel = new JPanel();

    private final ITPDSSLN ln;
    private final String id;
    private final Tecnico t;
    private final ListReparacoes caller;

    public Reparar(ITPDSSLN ln, String id, Tecnico t, ListReparacoes caller) {
        this.ln = ln;
        this.id = id;
        this.t = t;
        this.caller = caller;

        addActions();

        scrollPane.setViewportView(passoPanel);
        scrollPane.setPreferredSize(new Dimension(400,300));

        getProximoPasso();

        this.setTitle("Reparar equipamento");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addActions() {
        suspenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        próximoPassoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ln.concluirPasso(id, t);
                getProximoPasso();

            }
        });

    }

    private void getProximoPasso() {
        Passo p = ln.getInfoProximoPasso(id);
        if(p == null) {
            caller.updateList();
            dispose();
        }
        else {
            NovoPassosCard pc = new NovoPassosCard("1", p.getNomePasso(), 12, (int) p.getTempoPrevisto().toMinutes());
            for (Peca peca : p.getPecasEstimadas()) {
                PecasCard pecasCard = new PecasCard(peca.getNomePeca(), peca.getCusto(), peca.getQuantidade());
                pc.addPeca(pecasCard);
            }

            passoPanel = new JPanel();
            scrollPane.setViewportView(passoPanel);
            passoPanel.setLayout(new BoxLayout(passoPanel, BoxLayout.Y_AXIS));

            passoPanel.add(pc);
        }
    }
}
