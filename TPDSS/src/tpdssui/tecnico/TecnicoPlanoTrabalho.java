package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Registo;
import tpdssln.ssreparacoes.ReparacaoNormal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
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

    private ITPDSSLN ln;
    private Map<Integer, Passo> passos = new HashMap<>();
    private Registo r;
    private int counter = 1;

    private float orcamento = 0;

    public TecnicoPlanoTrabalho(ITPDSSLN ln) {

        this.ln = ln;

        Registo r = ln.maisUrgente();
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
                ln.registarPlanoTrabalho(r.getId(), passos);
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
