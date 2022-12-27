package orderngo.userinterface;

import orderngo.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog{
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginPanel;
    private JLabel restaurantImage;
    private JLabel loginImage;


    public LoginForm(JFrame parent) {
        JFrame loginFrame = new JFrame(); loginButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) { //aqui devia ser quando se carrega no botão login e quando se carrega no Enter
                dispose();
                String email = emailField.getText();
                String password = passwordField.getText();
                //GerenteForm que gere o seu restaurante e edita o cardapio
                if(email.equals("Gerente") && password.equals("GerentePassword")) {
                    App.GerenteMenu();
                }
                //GestorForm que adiciona/remove restaurantes de agueda da base de dados
                if(email.equals("Gestor") && password.equals("GestorPassword")) {
                    App.GestorMenu();
                }
            }
        });
        loginFrame.setTitle("Order-N-Go Login");
        loginFrame.setContentPane(loginPanel);
        loginFrame.setMinimumSize(new Dimension(750, 750));
        setModal(true);
        loginFrame.setLocationRelativeTo(parent);
        loginFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loginFrame.setVisible(true);

    }

    // TODO: usar SQL para login/pass e fechar e abrir MainForm

    private void createUIComponents() {
        restaurantImage = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        loginImage = new JLabel(new ImageIcon("src\\imageresources\\user.png"));
    }
}
