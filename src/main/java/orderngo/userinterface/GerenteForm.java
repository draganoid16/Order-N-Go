package orderngo.userinterface;

import orderngo.utilizador.Restaurante;
import orderngo.utilizador.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GerenteForm {

    private JLabel usernameCustom;
    private JLabel restauranteLabel;
    private JLabel cardapioLabel;
    private JLabel infoLabel;
    private JPanel mainPanel;
    private JLabel userImage;
    private JPanel menuPanel;
    private JPanel restaurantePanel;
    private JPanel cardapioPanel;
    private JPanel infoPanel;
    private JLabel restauranteName;
    private JLabel restauranteImage;
    private JLabel emailLabel;
    private JLabel moradaLabel;
    private JLabel telemovelLabel;
    private JLabel restauranteEmail;

    public GerenteForm(JFrame parent, String email) throws SQLException {



        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Order-N-Go Main");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setMinimumSize(new Dimension(1050, 750));
        mainFrame.setLocationRelativeTo(parent);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
        //panels comecam não visiveis e ficam visiveis on click no menu
        restaurantePanel.setVisible(false);
        cardapioPanel.setVisible(false);
        infoPanel.setVisible(false);
        //Restaurante Setup
        Utilizador utilizador = Restaurante.getRestaurante(email);
        usernameCustom.setText(utilizador.getNome());
        restauranteName.setText(utilizador.getNome());
        emailLabel.setText(utilizador.getEmail());
        moradaLabel.setText(utilizador.getMorada());
        telemovelLabel.setText(utilizador.getTelemovel());
        // TODO: IMAGEM RESTAURANTE

        restauranteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuPanel.setVisible(true);
                cardapioPanel.setVisible(false);
                infoPanel.setVisible(false);
                restaurantePanel.setVisible(true);
                System.out.println("carregado");
            }
        });

        cardapioLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //define visibilidade de paineis
                menuPanel.setVisible(true);
                restaurantePanel.setVisible(false);
                infoPanel.setVisible(false);
                cardapioPanel.setVisible(true);
                System.out.println("carregado");
            }
        });
        infoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuPanel.setVisible(true);
                restaurantePanel.setVisible(false);
                cardapioPanel.setVisible(false);
                infoPanel.setVisible(true);
                System.out.println("carregado");
            }
        });
    }

    private void createUIComponents() {
        userImage = new JLabel(new ImageIcon("src\\imageresources\\profile.png"));
        usernameCustom = new JLabel("placeholder");
        usernameCustom.setFont(new Font("Arial Black", Font.PLAIN, 14));
        //info restaurante
        restauranteName = new JLabel("blah blah restaurante"); //puxar de bd
        restauranteImage = new JLabel(new ImageIcon("src\\imageresources\\restauranteimageexample.jpg"));
        emailLabel = new JLabel("examploemail");
        moradaLabel = new JLabel("exemplomorada");
        telemovelLabel = new JLabel("exemplotelemovel");
    }
}
