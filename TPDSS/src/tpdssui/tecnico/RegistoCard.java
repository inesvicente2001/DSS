package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.Registo;
import tpdssln.ssreparacoes.excecoes.RegistoNaoExisteException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistoCard extends JPanel {
    private JPanel panel1;
    private JButton repararButton;
    private JLabel nomeLabel;
    private JLabel urgenciaLabel;
    private JLabel prazoLabel;
    private JLabel dataPedido;

    private ITPDSSLN ln;
    private final String id;


    public RegistoCard(ITPDSSLN ln, String id) {
        this.ln = ln;
        this.id = id;

        this.setLayout(new CardLayout());

        try {
            String[] info = ln.obterInfoRegistoNConcluido(id).split(";");

            Border b = BorderFactory.createEtchedBorder();
            this.setBorder(BorderFactory.createTitledBorder(b, info[0]));

            nomeLabel.setText("ID: " + id);
            urgenciaLabel.setText("Urgência: " + info[1]);
            dataPedido.setText("Pedido: " + info[2]);
            prazoLabel.setText("Prazo: " + info[3]);

            addActions();

            this.add(panel1);
        } catch (RegistoNaoExisteException e) {
            e.printStackTrace();
        }
    }

    public void addActions() {
        repararButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reparar(ln, id);
            }
        });

    }
}
