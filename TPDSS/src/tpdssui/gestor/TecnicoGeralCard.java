package tpdssui.gestor;

import tpdssln.ssempregados.Tecnico;

import javax.swing.*;
import javax.swing.border.Border;

public class TecnicoGeralCard extends JPanel{
    private JPanel panel1;
    private JLabel numReparacoes;
    private JLabel duracaoMedia;
    private JLabel mediaDesvio;

    public TecnicoGeralCard(Tecnico t) {
        numReparacoes.setText("Reparações: " + t.getReparacoes().size());
        duracaoMedia.setText("Duração Média: " + t.getDuracaoMedia().getSeconds()/60);
        mediaDesvio.setText("Média Desvio: " + t.getMediaDesvio().getSeconds()/60);
        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(BorderFactory.createTitledBorder(b, t.getNome()));

        this.add(panel1);
    }
}
