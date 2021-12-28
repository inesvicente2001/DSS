package tpdssui;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame{
    private JLabel image=new JLabel(new ImageIcon("TPDSS/src/logo.png")); //depois mudar isto.....
    private JLabel titleUP = new JLabel("Minho Repairs");
    private JProgressBar progressBar=new JProgressBar();
    private JLabel message=new JLabel();//Crating a JLabel for displaying the message

    private ITPDSSLN ln;

    public Loading(ITPDSSLN ln) {
        this.ln = ln;

        createGUI();
        addImage();
        addText();
        addProgressBar();
        addMessage();
        runningPBar();
    }



    public Loading() {
        this.ln = new TPDSSLNFacade();

        createGUI();
        addImage();
        addText();
        addProgressBar();
        addMessage();
        runningPBar();
    }
    public void createGUI(){
        this.setTitle("Minho Repairs");
        this.getContentPane().setLayout(null);
        this.setBounds(150, 150, 1200,800);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }
    public void addImage(){
        image.setSize(1200,600);
        this.add(image);
    }
    public void addText()
    {
        titleUP.setFont(new Font("arial",Font.BOLD,30));
        titleUP.setBounds(470,440,1200,80);
        titleUP.setForeground(Color.RED);
        this.add(titleUP);
    }
    public void addMessage()
    {
        message.setBounds(550,740,200,40);//Setting the size and location of the label
        message.setForeground(Color.black);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        this.add(message);//adding label to the frame
    }
    public void addProgressBar(){
        progressBar.setBounds(200,560,800,60);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        this.add(progressBar);
    }
    public void runningPBar(){
        int i=0;//Creating an integer variable and intializing it to 0

        while( i<=100)
        {
            try{
                Thread.sleep(17);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i++;
                if(i==100){
                    this.dispose();
                    new Login(ln);
                }

            }catch(Exception e){
                e.printStackTrace();
            }



        }
    }
}
