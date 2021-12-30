package tpdssui.gestor;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Empregado;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ListaGeralTecnico extends JFrame{
    private JPanel panel1;
    private JButton voltarButton;
    private JPanel tecnPanel;

    private ITPDSSLN ln;

    public ListaGeralTecnico(ITPDSSLN ln) {

        this.ln = ln;

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });


        Map<String, Tecnico> tecnicos = ln.acederTecnicos();

        tecnPanel.setLayout(new BoxLayout(tecnPanel,BoxLayout.Y_AXIS));

        //Iterar o Mapa e ir atualizando o Panel
        for (Map.Entry<String, Tecnico> entry : tecnicos.entrySet()){
            
            TecnicoGeralCard fc =  new TecnicoGeralCard(entry.getValue());
            tecnPanel.add(fc);

            tecnPanel.add(Box.createRigidArea(new Dimension(0,10)));

        }

        this.setTitle("Lista de Informações Gerais sobre Técnicos");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
