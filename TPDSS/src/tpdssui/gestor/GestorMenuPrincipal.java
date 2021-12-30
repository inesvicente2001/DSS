package tpdssui.gestor;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Gestor;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestorMenuPrincipal extends JFrame {
    private JButton lstInfoGeralTec;
    private JButton lstInfosFunc;
    private JButton logoutButton;
    private JPanel topPanel;
    private JButton lstInfosPormTec;
    private JLabel welcome;


    private final ITPDSSLN ln;
    private final String id;



    public GestorMenuPrincipal(ITPDSSLN ln, String id) {

        this.ln = ln;
        this.id = id;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(ln);
                dispose();

            }
        });

        lstInfoGeralTec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaGeralTecnico(ln);

            }
        });

        lstInfosFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaFuncionarios(ln);

            }
        });

        lstInfosPormTec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PormenoresTecnico(ln);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ln.save();
            }
        });

        try {
            this.welcome.setText("Bem vindo, " + ln.verEmpregado(id).getNome());
        } catch (EmpregadoNaoExisteException e) {
            new Login(ln);
            dispose();
        }
        this.setTitle("Gestor");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
