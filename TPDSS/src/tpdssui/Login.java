package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    private Container container = getContentPane();
    private JLabel userLabel = new JLabel("ID UTILIZADOR");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");
    private JCheckBox showPassword = new JCheckBox("Mostrar Password");

    private ITPDSSLN ln;


    public Login() {
        this.ln = new TPDSSLNFacade();

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        this.setTitle("Login");
        this.setBounds(150, 150, 1200,800);
        //this.setSize(1200,800);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(400, 250, 150, 30);
        passwordLabel.setBounds(400, 320, 100, 30);
        userTextField.setBounds(510, 250, 250, 30);
        passwordField.setBounds(510, 320, 250, 30);
        showPassword.setBounds(510, 350, 250, 30);
        loginButton.setBounds(450, 400, 100, 30);
        resetButton.setBounds(600, 400, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("admin12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                //Aqui terá de ir para uma nova frame consoante o tipo de utilizador
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

}

