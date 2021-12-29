package tpdssui.admin;

import tpdssln.ITPDSSLN;
import tpdssln.ssempregados.Empregado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditarEmpregado extends JFrame {
    private JPanel panel1;
    private JPanel thirdPanel;
    private JCheckBox editarPasswordCheckBox;
    private JPasswordField passwordField1;
    private JCheckBox editarNomeCheckBox;
    private JTextField nomeTextField;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField idTextField;
    private JRadioButton mostrarPasswordRadioButton;
    private JPanel secndPanel;
    private JPanel fourthPanel;
    private JPanel firstPanel;

    private ITPDSSLN ln;

    public EditarEmpregado(ITPDSSLN ln) {
        this.ln = ln;

        setDefaultVisibilities();
        addActions();
        flipMostrarPassword();
        addKeyStrokes();

        // Definir o butão default
        this.getRootPane().setDefaultButton(confirmarButton);
        // Definir o título
        this.setTitle("Editar Empregado");
        // Quando fechamos a janela não fechamos o programa
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Definir o painel do conteúdo
        this.setContentPane(panel1);
        // Definir o tamanho da janela e a posição desta para o meio do ecrã
        this.pack();
        this.setLocationRelativeTo(null);
        // Definir a visibilidade
        this.setVisible(true);
    }

    // Definimos que paineis estão visíveis quando a janela é criada
    private void setDefaultVisibilities() {
        firstPanel.setVisible(true);
        secndPanel.setVisible(false);
        thirdPanel.setVisible(false);
        fourthPanel.setVisible(true);
    }

    // Adicionar ações aos vários elementos da janela
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
        editarNomeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipSecndPanelVisibility();
            }
        });
        editarPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipThirdPanelVisibility();
            }
        });

    }

    // Quando o utilizaaor clica no radiobutton, mostrar ou esconder a password
    private void flipMostrarPassword() {
        passwordField1.setEchoChar(mostrarPasswordRadioButton.isSelected() ? (char) 0 : '*');
    }

    /** Quando o utilizador clica no checkbox do nome, o segundo paine torna-se visível/invisível
     *  e ajusta-se o tamanho da janela de acordo.
     */
    private void flipSecndPanelVisibility() {
        secndPanel.setVisible(editarNomeCheckBox.isSelected());
        this.pack();
    }

    /**
     *  Quando o utilizador clica no checkbox da password, o terceiro painel torna-se visível/invisível
     *  e ajusta-se o tamanho da janela de acordo.
     */
    private void flipThirdPanelVisibility() {
        thirdPanel.setVisible(editarPasswordCheckBox.isSelected());
        this.pack();
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

    /**
     *  Quando o utilizador confirma as alterações obtemos o id da caixa de texto
     *  e verificamos cada uma das checkboxes. Caso esta(s) esteja(m) selecionada(s),
     *  obtemos o texto da caixa de texto respetiva e executamos o método de edição adequado.
     */
    private void confirmar() {
        String id = idTextField.getText();

        if(editarNomeCheckBox.isSelected()) {
            ln.editarNome(id,nomeTextField.getText());
        }
        if(editarPasswordCheckBox.isSelected()) {
            String password = new String(passwordField1.getPassword());
            ln.editarPassword(id,password);
        }
        for(Empregado e: ln.acederTecnicos().values()) {
            System.out.println(e.getNome() + "| ID: " + e.getId() + "| Password: " + e.getPassword());
        }
        for(Empregado e: ln.acederFuncionarios().values()) {
            System.out.println(e.getNome() + "| ID: " + e.getId() + "| Password: " + e.getPassword());
        }
        dispose();
    }

    // Quando o utilizador cancela a operação, destruímos a janela.
    private void cancelar() {
        dispose();
    }
}
