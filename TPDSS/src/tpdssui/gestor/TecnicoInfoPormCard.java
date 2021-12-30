package tpdssui.gestor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TecnicoInfoPormCard extends JPanel{
    private JPanel panel1;
    private JScrollPane tecnicoInfoPormScrollPanel;
    private JPanel tecnicoInfoPormPanel;

    public TecnicoInfoPormCard(List<String> planosTrabalho, String idTecnico) {


        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        for (String s : planosTrabalho){



            String[] lst = s.split("%");


            ReparacaoInfoCard tecnCard =  new ReparacaoInfoCard(lst);
            this.add(tecnCard);

            tecnCard.add(Box.createRigidArea(new Dimension(0,10)));




        }

        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(BorderFactory.createTitledBorder(b,idTecnico));

    }

}
