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

public class ListaFuncionarios extends JFrame{
    private JPanel panel1;
    private JButton voltarButton;
    private JPanel funcPanel;

    private ITPDSSLN ln;

    public ListaFuncionarios(ITPDSSLN ln) {

        this.ln = ln;

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });


        Map<String, Funcionario> funcionarios = ln.acederFuncionarios();

        funcPanel.setLayout(new BoxLayout(funcPanel,BoxLayout.Y_AXIS));

        //Iterar o Mapa e ir atualizando o Panel
        for (Map.Entry<String, Funcionario> entry : funcionarios.entrySet()){

           
            FuncionarioCard fc =  new FuncionarioCard((Funcionario) entry.getValue());
            funcPanel.add(fc);

            funcPanel.add(Box.createRigidArea(new Dimension(0,10)));

        }




        this.setTitle("Lista de Informações sobre Funcionários");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
