package tpdssui.gestor;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Gestor;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorMenuPrincipal extends JFrame {
    private JButton lstInfoGeralTec;
    private JButton lstInfosFunc;
    private JButton logoutButton;
    private JPanel topPanel;
    private JButton lstInfosPormTec;
    private JLabel welcome;


    private ITPDSSLN ln;
    private Gestor gestor;



    public GestorMenuPrincipal(ITPDSSLN ln, Gestor autenticado) {

        this.ln = ln;
        this.gestor = autenticado;

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

            }
        });

        lstInfosFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        lstInfosPormTec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.welcome.setText("Bem vindo, " + gestor.getNome());
        this.setTitle("Gestor");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
