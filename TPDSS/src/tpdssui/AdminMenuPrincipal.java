package tpdssui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuPrincipal extends JFrame {
    private JButton adicionarEmpregadoButton;
    private JButton editarEmpregadoButton;
    private JButton removerEmpregadoButton;
    private JButton logOutButton;

    public AdminMenuPrincipal() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });
    }
}
