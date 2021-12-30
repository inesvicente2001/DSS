package tpdssui.gestor;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PormenoresTecnico extends JFrame{
    private JPanel panel1;
    private JButton voltarButton;
    private JScrollPane geralScrollPanel;
    private JPanel geralPanel;

    private ITPDSSLN ln;

    public PormenoresTecnico(ITPDSSLN ln) {

        this.ln = ln;

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });


        geralPanel.setLayout(new BoxLayout(geralPanel,BoxLayout.Y_AXIS));



        Map<String, List<String>> infosPlano = ln.todosPlanosTrabalho();


        //Iterar o Mapa e ir atualizando o Panel
        for (Map.Entry<String, List<String>> entry : infosPlano.entrySet()){


            if(entry.getValue().size() > 0){
                TecnicoInfoPormCard tecnCard =  new TecnicoInfoPormCard(entry.getValue(), entry.getKey());

                //Border b = BorderFactory.createLoweredBevelBorder();
                //geralPanel.setBorder(BorderFactory.createTitledBorder(b,entry.getKey()));

                geralPanel.add(tecnCard);

                geralPanel.add(Box.createRigidArea(new Dimension(0,10)));
            }

        }


        this.setTitle("Lista de Informações Pormenorizadas sobre Técnicos");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
