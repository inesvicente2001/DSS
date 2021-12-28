package tpdssui.tecnico;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssui.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TecnicoMenuPrincipal extends JFrame {
    private JButton logoutButton;
    private JButton reparaçõesButton;
    private JButton pedidosDeOrçamentoButton;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel panel1;

    private ITPDSSLN ln;

    public TecnicoMenuPrincipal(ITPDSSLN ln) {
        this.ln = ln;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(ln);
                dispose();
            }
        });


        this.setTitle("Técnico");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public TecnicoMenuPrincipal() {
        this.ln = new TPDSSLNFacade();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });


        this.setTitle("Técnico");
        this.setContentPane(this.topPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    

}
