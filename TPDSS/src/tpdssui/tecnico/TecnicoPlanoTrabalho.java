package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Registo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

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
    private JButton cancelarButton;
    private JPanel passosPanel = new JPanel();
    private JScrollPane scrollPane;
    private JTextField prazoLabel;

    private ITPDSSLN ln;
    private final Map<Integer, Passo> passos = new HashMap<>();
    private final Registo r;
    private int counter = 1;

    private float orcamento = 0;

    public TecnicoPlanoTrabalho(ITPDSSLN ln) {

        this.ln = ln;

        r = ln.maisUrgente();
        String html = ln.toHTMLDescricao(r.getId());
        idEquipamento.setText(r.getId());
        nomeEquipamento.setText(r.getNomeEquipamento());
        descricao.setText(html);
        dataRececao.setText(DateTimeFormatter.ISO_DATE.format(r.getData()));

        passosPanel.setLayout(new BoxLayout(passosPanel,BoxLayout.Y_AXIS));

        scrollPane.setPreferredSize(new Dimension(700, 300));
        scrollPane.setViewportView(passosPanel);

        addActions();

        this.setTitle("Definir PLano de Trabalhos");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addPassoCard(NovoPassosCard pc, float custo) {
        passosPanel.add(pc);
        passosPanel.revalidate();

        orcamento += custo;
        orcamentoAtual.setText(orcamento + " €");
        orcamentoAtual.revalidate();
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate prazo = LocalDate.parse(prazoLabel.getText(), formatter);
                ln.registarPlanoTrabalho(r.getId(), passos, prazo.atStartOfDay());
                dispose();
            }
        });
        adicionarPassoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPasso();
            }
        });

    }

    private void adicionarPasso() {
        new AdicionarPasso(counter + "", passos, this);
        counter++;
    }
}
