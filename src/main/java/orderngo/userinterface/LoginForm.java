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
            public void actionPerformed(ActionEvent e) {
                dispose();
                String email = emailField.getText();
                String password = passwordField.getText();
                if(email.equals("teste") && password.equals("teste")) {
                    App.MainMenu();
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
        restaurantImage = new JLabel(new ImageIcon("C:\\Users\\Joao\\Desktop\\Order-N-Go\\src\\food-tray.png"));
        loginImage = new JLabel(new ImageIcon("C:\\Users\\Joao\\Desktop\\Order-N-Go\\src\\login.png"));
    }
}
