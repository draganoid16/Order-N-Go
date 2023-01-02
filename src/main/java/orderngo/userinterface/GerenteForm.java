package orderngo.userinterface;

import orderngo.cardapio.Bebida;
import orderngo.cardapio.Prato;
import orderngo.utilizador.Restaurante;
import orderngo.utilizador.Utilizador;

import javax.swing.*;
import javax.swing.border.Border;
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
    private JLabel prato1;
    private JLabel prato2;
    private JLabel prato3;
    private JLabel bebida1;
    private JLabel bebida2;
    private JLabel bebida3;
    private JLabel restauranteEmail;

    public GerenteForm(JFrame parent, String email) throws SQLException {


        //setup basico
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Order-N-Go Main");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setMinimumSize(new Dimension(1050, 850));
        mainFrame.setLocationRelativeTo(parent);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
        //setup detalhado
        restauranteSetup(email);
        //cardapio
        adicionarPratodeSQL(Restaurante.getRestaurante(email));
        adicionarBebidadeSQL(Restaurante.getRestaurante(email));

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

    private void restauranteSetup(String email) throws SQLException {
        //panels comecam não visiveis e ficam visiveis on click no menu
        restaurantePanel.setVisible(false);
        cardapioPanel.setVisible(false);
        infoPanel.setVisible(false);
        //Restaurante Setup
        Border border = BorderFactory.createLineBorder(Color.white);
        Utilizador utilizador = Restaurante.getRestaurante(email);
        Restaurante restaurante = Restaurante.getRestaurante(email);
        usernameCustom.setText(utilizador.getNome());
        restauranteName.setText(utilizador.getNome());
        restauranteName.setBorder(border);
        emailLabel.setText(utilizador.getEmail());
        moradaLabel.setText(utilizador.getMorada());
        telemovelLabel.setText(utilizador.getTelemovel());
        //Setup imagem
        Image img = restaurante.getImagem();
        Icon icon = resizeImage(img, restauranteImage);
        restauranteImage.setIcon(icon);
        restauranteImage.setBorder(border);
    }

    public Icon resizeImage(Image image, JLabel restauranteImage){
        Image resizedimg = image.getScaledInstance(restauranteImage.getWidth(), restauranteImage.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(resizedimg);
    }

    public Icon pratoImage(Image image){
        Image pratoImage = image.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        return new ImageIcon(pratoImage);
    }

    public Icon bebidaImage(Image image){
        Image bebidaImage = image.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        return new ImageIcon(bebidaImage);
    }

    private void adicionarPratodeSQL(Restaurante restaurante) throws SQLException {
        Prato[] pratos = Prato.from(restaurante);

        for(int i=0; i<pratos.length; i++){
            JLabel[] pratosVariaveis = {prato1, prato2, prato3};
            String tipoprato = String.valueOf(pratos[i].getNome());
            pratosVariaveis[i].setText(tipoprato);

            Image img = pratos[i].getImagem();
            Icon icon = pratoImage(img);

            pratosVariaveis[i].setIcon(icon);
        }

    }
        //TODO: da para otimizar estas funções so numa, mas para efeitos de teste (so adicionar pratos, ou so adicionar bebidas), fica por agora assim
        //TODO: otimizar se pratos/bebidas image for null

    private void adicionarBebidadeSQL(Restaurante restaurante) throws SQLException {
        Bebida[] bebidas = Bebida.from(restaurante);

        for(int i=0; i<bebidas.length; i++){
            JLabel[] pratosVariaveis = {bebida1, bebida2, bebida3};
            String tipoprato = String.valueOf(bebidas[i].getNome());
            pratosVariaveis[i].setText(tipoprato);

            Image img = bebidas[i].getImagem();
            Icon icon = bebidaImage(img);

            pratosVariaveis[i].setIcon(icon);
        }

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
        prato1 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        prato2 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        prato3 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        bebida1 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        bebida2 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        bebida3 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));

    }
}
