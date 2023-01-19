package orderngo.userinterface;

import orderngo.exception.RestauranteNotFoundException;
import orderngo.utilizador.GestorOrderAndGo;
import orderngo.utilizador.Restaurante;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
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
    private JLabel orderngologo;


    public LoginForm(JFrame parent) {
        JFrame loginFrame = new JFrame();
        try {
            loginFrame.setIconImage(ImageIO.read(new File("src\\imageresources\\orderngo.png")));
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
        //RadioButtons agrupados
        ButtonGroup group = new ButtonGroup();
        group.add(restauranteRadioButton);
        group.add(gestorONGRadioButton);
        //butão enter
        loginFrame.getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                String email = emailField.getText();
                // String password = passwordField.getText(); // Deprecated
                char[] password = passwordField.getPassword();

                //GerenteForm que gere o seu restaurante e edita o cardapio
                try {
                    if (Restaurante.validarCredenciais(email, password) && restauranteRadioButton.isSelected()) {
                        GerenteForm gerenteForm = new GerenteForm(null, email);
                        loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));

                        //GestorForm que adiciona/remove restaurantes de agueda da base de dados
                    } else if (GestorOrderAndGo.validarCredenciais(email, password) && gestorONGRadioButton.isSelected()) {
                        GestorForm gestorForm = new GestorForm(null,email);
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
        orderngologo = new JLabel(new ImageIcon("src\\imageresources\\orderngo.png"));
    }
}
