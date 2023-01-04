package orderngo.userinterface;

import orderngo.App;
import orderngo.exceptions.RestauranteNotFoundException;
import orderngo.utilizador.GestorOrderAndGo;
import orderngo.utilizador.Restaurante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class LoginForm extends JDialog {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginPanel;
    private JLabel restaurantImage;
    private JLabel loginImage;
    private JRadioButton restauranteRadioButton;
    private JRadioButton gestorONGRadioButton;


    public LoginForm(JFrame parent) {
        JFrame loginFrame = new JFrame();
        //RadioButtons agrupados
        ButtonGroup group = new ButtonGroup();
        group.add(restauranteRadioButton);
        group.add(gestorONGRadioButton);
        //butão enter
        loginFrame.getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //aqui devia ser quando se carrega no botão login e quando se carrega no Enter
                dispose();
                String email = emailField.getText();
                String password = passwordField.getText();


                //GerenteForm que gere o seu restaurante e edita o cardapio
                try {
                    if (Restaurante.validCredentials(email, password) && restauranteRadioButton.isSelected()) {
                        GerenteForm gerenteForm = new GerenteForm(null, email);
                        loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

                        //GestorForm que adiciona/remove restaurantes de agueda da base de dados
                    } else if (GestorOrderAndGo.validCredentials(email, password) && gestorONGRadioButton.isSelected()) { //trocar isto
                        App.GestorMenu();
                        loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

                    } else {
                        JOptionPane.showMessageDialog(loginFrame, "Erro! Credencias introduzidos não são validos, ou conta selecionada incorreta!", "Login/Seleção Incorreto!", JOptionPane.ERROR_MESSAGE);
                        throw new RestauranteNotFoundException(email);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(loginFrame, "Erro! Conexão a Base de Dados não foi estabelecido!", "Conexão não Estabelecida!", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
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


    private void createUIComponents() {
        restaurantImage = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        loginImage = new JLabel(new ImageIcon("src\\imageresources\\user.png"));
    }
}
