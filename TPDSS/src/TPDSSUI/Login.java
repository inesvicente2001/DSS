package TPDSSUI;

import javax.swing.*;

public class Login {

    public static void main(String[] a) {
        //Juntar as 2 num
        SplashScreenDemo splashScreen = new SplashScreenDemo();
        splashScreen.runningPBar();

        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}
