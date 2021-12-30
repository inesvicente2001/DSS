package tpdssui.gestor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ReparacaoInfoCard extends JPanel{
    private JPanel panel1;
    private JLabel custoPecasTotal;
    private JLabel planoTrabalhosTxt;


    public ReparacaoInfoCard(String[] infos) {

        this.setLayout(new CardLayout());

        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));

        custoPecasTotal.setText("Custo Total: " + infos[1]);
        planoTrabalhosTxt.setText(infos[2]);

        Border b = BorderFactory.createLoweredBevelBorder();
        panel1.setBorder(BorderFactory.createTitledBorder(b, infos[0]));

        //panel1.add(Box.createRigidArea(new Dimension(0,10)));

        this.add(panel1);


    }
}
