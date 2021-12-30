package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.Registo;
import tpdssln.ssreparacoes.excecoes.RegistoNaoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListReparacoes extends JFrame {
    private JPanel panel1;
    private JButton voltarButton;
    private JScrollPane scrollPane;
    private JPanel registosPanel = new JPanel();

    private ITPDSSLN ln;

    public static void main(String[] args) {
        ITPDSSLN ln = new TPDSSLNFacade();

        try {
            Funcionario t = (Funcionario) ln.verEmpregado("Furry");
            String id = ln.adicionarPedidoOrcamentoNormal("ola", 2, "q", "aqui", "eu", "123", "9123", "email");
            ln.registarPlanoTrabalho(id, new HashMap<>());
            String id1 = ln.adicionarPedidoOrcamentoNormal("ola1", 21, "q1", "aqui1", "eu1", "1231", "91231", "email1");
            ln.registarPlanoTrabalho(id1, new HashMap<>());
            String id2 = ln.adicionarPedidoOrcamentoNormal("ola2", 22, "q2", "aqui2", "eu2", "1232", "91232", "email2");
            ln.registarPlanoTrabalho(id2, new HashMap<>());
            String id3 = ln.adicionarPedidoOrcamentoNormal("ola3", 23, "q3", "aqui3", "eu3", "1233", "91233", "email3");
            ln.registarPlanoTrabalho(id3, new HashMap<>());
            new ListReparacoes(ln);
        } catch (EmpregadoNaoExisteException e) {
            e.printStackTrace();
        }


    }

    public ListReparacoes(ITPDSSLN ln) {
        this.ln = ln;
        Set<String> registos = ln.getRegistosNConcluidos();

        scrollPane.setViewportView(registosPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        registosPanel.setLayout(new BoxLayout(registosPanel, BoxLayout.Y_AXIS));

        for(String id: registos) {
            RegistoCard rc = new RegistoCard(ln, id);
            registosPanel.add(rc);
        }
        addActions();

        this.setTitle("Registos não reparados");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);this.setVisible(true);
    }

    public void addActions() {
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
