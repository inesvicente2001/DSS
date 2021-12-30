package tpdssui.tecnico;

import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Peca;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdicionarPasso extends JFrame {
    private JPanel panel1;
    private JTextField nomeField1;
    private JTextField previsaoField2;
    private JButton adicionarPecaButton;
    private JCheckBox temSubPassosCheckBox;
    private JButton adicionarSubPassosButton;
    private JButton confirmarButton1;
    private JButton cancelarButton;
    private JPanel passosPanel;
    private JScrollPane pecasScrollPane;
    private JPanel pecasPanel;
    private JScrollPane subPassoScrollPanel;
    private JLabel orcamentoLabel;
    private JPanel pecaCardPanel = new JPanel();
    private JPanel passoCardPanel = new JPanel();

    private Map<Integer, Passo> passos;
    private Map<Integer, Passo> subPassos = new HashMap<>();
    private Set<Peca> pecas = new HashSet<>();
    private AdicionarPasso caller = null;
    private TecnicoPlanoTrabalho tpt = null;
    private Set<NovoPassosCard> children = new HashSet<>();

    private float orcamento = 0;

    private String id;
    private int counter = 1;

    public AdicionarPasso(String id, Map<Integer, Passo> passos, TecnicoPlanoTrabalho tpt) {
        this.id = id;
        this.passos = passos;
        this.tpt = tpt;

        pecaCardPanel.setLayout(new BoxLayout(pecaCardPanel, BoxLayout.Y_AXIS));
        pecasScrollPane.setViewportView(pecaCardPanel);
        pecasScrollPane.setPreferredSize(new Dimension(700, 300));

        passoCardPanel.setLayout(new BoxLayout(passoCardPanel, BoxLayout.Y_AXIS));
        subPassoScrollPanel.setViewportView(passoCardPanel);
        subPassoScrollPanel.setPreferredSize(new Dimension(700, 300));

        addActions();

        passosPanel.setVisible(false);

        this.setTitle("Criar Passo");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public AdicionarPasso(String id, Map<Integer, Passo> passos, AdicionarPasso caller) {
        this.id = id;
        this.passos = passos;
        this.caller = caller;

        pecaCardPanel.setLayout(new BoxLayout(pecaCardPanel, BoxLayout.Y_AXIS));
        pecasScrollPane.setViewportView(pecaCardPanel);
        pecasScrollPane.setPreferredSize(new Dimension(700, 300));

        passoCardPanel.setLayout(new BoxLayout(passoCardPanel, BoxLayout.Y_AXIS));
        subPassoScrollPanel.setViewportView(passoCardPanel);
        subPassoScrollPanel.setPreferredSize(new Dimension(700, 300));

        addActions();

        passosPanel.setVisible(false);

        this.setTitle("Criar Passo");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addActions() {
        confirmarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField1.getText();
                Duration d = Duration.ofMinutes(Integer.parseInt(previsaoField2.getText()));
                Passo p = new Passo(nome, d, pecas, subPassos);
                passos.put(passos.size(), p);

                NovoPassosCard pc = new NovoPassosCard(id, p.getNomePasso(), 32, (int) p.getTempoPrevisto().toMinutes());
                for(Peca peca: pecas) {
                    PecasCard pecaCard = new PecasCard(peca.getNomePeca(), peca.getCusto(), peca.getQuantidade());
                    pc.addPeca(pecaCard);
                }
                for(NovoPassosCard passoCard: children) {
                    pc.addPasso(passoCard);
                }

                if(caller != null) {
                    caller.addPassoCard(pc, orcamento);
                } else {
                    tpt.addPassoCard(pc, orcamento);
                }
                dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        temSubPassosCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipVisibility();
            }
        });
        adicionarPecaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPeca();
            }
        });
        adicionarSubPassosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPasso();
            }
        });
    }

    private void flipVisibility() {
        passosPanel.setVisible(temSubPassosCheckBox.isSelected());
        pecasPanel.setVisible(!temSubPassosCheckBox.isSelected());
        pack();
    }

    public void addPecaCard(PecasCard pc, float custo) {
        pecaCardPanel.add(pc);
        pecaCardPanel.revalidate();

        orcamento += custo;
        orcamentoLabel.setText(orcamento + " €");
        orcamentoLabel.revalidate();
    }

    public void addPassoCard(NovoPassosCard pc, float custo) {
        passoCardPanel.add(pc);
        passoCardPanel.revalidate();

        children.add(pc);

        orcamento += custo;
        orcamentoLabel.setText(orcamento + " €");
        orcamentoLabel.revalidate();
    }

    private void adicionarPeca() {
        new AdicionarPeca(pecas, this);

    }

    private void adicionarPasso() {
        new AdicionarPasso(id + "." + counter, subPassos, this);
        counter++;
    }
}
