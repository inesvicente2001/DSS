package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;
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

    private final ITPDSSLN ln;
    private Tecnico t;

    public ListReparacoes(ITPDSSLN ln, String id) {
        this.ln = ln;
        try {
            this.t = (Tecnico) ln.verEmpregado(id);
        } catch (EmpregadoNaoExisteException e) {
            dispose();
        }

        Set<String> registos = ln.getRegistosNConcluidos();

        scrollPane.setViewportView(registosPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        registosPanel.setLayout(new BoxLayout(registosPanel, BoxLayout.Y_AXIS));

        for(String rid: registos) {
            RegistoCard rc = new RegistoCard(ln, rid, t, this);
            registosPanel.add(rc);
        }
        addActions();

        this.setTitle("Registos não reparados");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addActions() {
        voltarButton.addActionListener(e -> dispose());

    }

    public void updateList() {
        registosPanel = new JPanel();

        registosPanel.setLayout(new BoxLayout(registosPanel,BoxLayout.Y_AXIS));
        scrollPane.setViewportView(registosPanel);

        for(String rid: ln.getRegistosNConcluidos()) {
            RegistoCard rc = new RegistoCard(ln, rid, t, this);
            registosPanel.add(rc);
        }

    }
}
