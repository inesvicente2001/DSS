package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    private JButton startButton = new JButton("Começar");
    private JLabel image=new JLabel(new ImageIcon("TPDSS/src/logo.png")); //depois mudar isto.....
    private JLabel titleUP = new JLabel("Minho Repairs");

    private ITPDSSLN ln;

    public Start() {
        this.ln = new TPDSSLNFacade();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Loading();

            }
        });

        image.setSize(1200,600);
        this.add(image);

        titleUP.setFont(new Font("arial",Font.BOLD,30));
        titleUP.setBounds(470,440,1200,80);
        titleUP.setForeground(Color.RED);
        this.add(titleUP);

        startButton.setBounds(470,600,120,80);
        this.add(startButton);


        this.setTitle("Minho Repairs Start Page");
        this.getContentPane().setLayout(null);
        this.setBounds(150, 150, 1200,800);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);



    }





}
