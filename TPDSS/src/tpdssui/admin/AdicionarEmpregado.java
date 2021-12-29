package tpdssui.admin;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Empregado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AdicionarEmpregado extends JFrame {
    public static final String[] tipoEmpregado = { "Funcionário", "Técnico", "Gestor" };

    private JTextField textField1;
    private JPanel panel1;
    private JButton confirmarButton;
    private JComboBox<String> comboBox1;
    private JPasswordField passwordField1;
    private JButton cancelarButton;
    private JRadioButton mostrarPasswordRadioButton;
    private JCheckBox mostrarPasswordCheckBox;

    private ITPDSSLN ln;

    public AdicionarEmpregado(ITPDSSLN ln) {
        this.ln = ln;

        addItems();
        addActions();
        addKeyStrokes();

        // Definir o butão default
        this.getRootPane().setDefaultButton(confirmarButton);

        this.setTitle("Adicionar Empregado");

        // Quando fechamos esta janela apenas destruímos esta janela não fechamos o programa
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Definimos o painel de conteúdo
        this.setContentPane(panel1);

        // Definir o tamanho da janela e a posição desta para o centro do ecrã
        this.pack();
        this.setLocationRelativeTo(null);
        // Janela é vísivel
        this.setVisible(true);
    }

    // Adicionar os itens da lista de funcionários
    private void addItems() {
        for(String s: tipoEmpregado) {
            comboBox1.addItem(s);
        }
    }

    // Designar o que cada butão faz
    private void addActions() {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        mostrarPasswordRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipMostrarPassword();
            }
        });
    }

    // Designar o que os butões devem fazer
    private void addKeyStrokes() {
        panel1.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    //  Quando o utilizador clica em confirmar este método é executado
    private void confirmar() {
        // Obter username, password e o tipo de empregado
        String tipo = String.valueOf(comboBox1.getSelectedItem());
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());

        // Dependendo do tipo de empregado executar o método de criação de empregado apropriado
        switch (tipo) {
            case "Funcionário"  : ln.adicionarFuncionario(username,password); break;
            case "Técnico"      : ln.adicionarTecnico(username,password); break;
            case "Gestor"       : ln.adicionarGestor(username, password); break;
        }
        for(Empregado e: ln.acederTecnicos().values()) {
            System.out.println(e.getNome() + "| ID: " + e.getId() + "| Password: " + e.getPassword());
        }
        for(Empregado e: ln.acederFuncionarios().values()) {
            System.out.println(e.getNome() + "| ID: " + e.getId() + "| Password: " + e.getPassword());
        }
        dispose();
    }

    // Caso o utilizador cancele a operação destruir a janela
    private void cancelar() {
        dispose();
    }

    // Caso o utilizador clique na check box trocar o estado do texto de visível para invisível e vice-versa
    private void flipMostrarPassword() {
        passwordField1.setEchoChar(mostrarPasswordRadioButton.isSelected() ? (char) 0 : '*');
    }
}
