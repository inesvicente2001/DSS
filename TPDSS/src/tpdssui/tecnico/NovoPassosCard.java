package tpdssui.tecnico;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NovoPassosCard extends JPanel {
    private JPanel panel1;
    private JLabel nomeLabel;
    private JLabel custoLabel;
    private JLabel tempoLabel;
    private JScrollPane pecasScrollPane;
    private JScrollPane passosScrollPane;
    private final JPanel pecasPanel = new JPanel();
    private final JPanel passosPanel = new JPanel();

    public NovoPassosCard(String id, String nome, float custo, int tempo) {
        // Copiar o texto para as labels
        nomeLabel.setText(nome);
        custoLabel.setText(custo + " €");
        tempoLabel.setText(tempo + " mins");

        // Definir os layout managers
        this.setLayout(new CardLayout());
        pecasPanel.setLayout(new BoxLayout(pecasPanel, BoxLayout.Y_AXIS));
        passosPanel.setLayout(new BoxLayout(passosPanel, BoxLayout.Y_AXIS));

        // Definir as bordas
        Border b = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createTitledBorder(b, "Passo " + id));

        // Definir o tamanho do painel de scroll
        pecasScrollPane.setPreferredSize(new Dimension(-1, 200));
        pecasScrollPane.setViewportView(pecasPanel);

        passosScrollPane.setPreferredSize(new Dimension(-1, 300));
        passosScrollPane.setViewportView(passosPanel);
        passosScrollPane.setVisible(false);

        // Adicionar o painel ao nosso painel
        this.add(panel1);
    }

    public void setInfo(int id, String nome, float custo, int tempo) {
        nomeLabel.setText(nome);
        custoLabel.setText(custo + " €");
        tempoLabel.setText(tempo + " mins");

        // Definir as bordas
        Border b = BorderFactory.createEtchedBorder();
        this.setBorder(BorderFactory.createTitledBorder(b, "Passo " + id));
    }

    public void addPeca(PecasCard pc) {
        // Definir o alinhamento
        pecasPanel.add(pc);
        pecasPanel.revalidate();
    }

    public void addPasso(NovoPassosCard pc) {
        pecasScrollPane.setVisible(false);
        passosScrollPane.setVisible(true);
        passosPanel.add(pc);
        passosPanel.revalidate();
    }
}
