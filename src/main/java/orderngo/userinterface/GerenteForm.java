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
    private JLabel prato3;
    private JLabel prato2;
    private JLabel bebida1;
    private JLabel bebida2;
    private JLabel bebida3;
    private JPanel menu1;
    private JPanel menu2;
    private JPanel menu3;
    private JLabel helpIcon;
    private JLabel restauranteEmail;

    public GerenteForm(JFrame parent, String email) throws SQLException {


        //setup basico
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Order-N-Go Main");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setMinimumSize(new Dimension(1050, 1050));
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

            @Override
            public void mouseEntered(MouseEvent e) {
                restauranteLabel.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restauranteLabel.setForeground(Color.BLACK);
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

            @Override
            public void mouseEntered(MouseEvent e) {
                cardapioLabel.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cardapioLabel.setForeground(Color.BLACK);
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

            @Override
            public void mouseEntered(MouseEvent e) {
                infoLabel.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoLabel.setForeground(Color.BLACK);
            }
        });

        prato1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String[] arr = getInfoPrato(email, 0);
                    JOptionPane.showMessageDialog(mainFrame, "Info do Prato \n" + "Nome do Prato: " + arr[0] + "\n" + "Detalhes do Prato: " + arr[1] + "\n"
                    + "Preço do Prato: " + arr[2] + "€" + "\n" + "Tipo de Prato: "+ arr[3] + "\n" + "Alergenicos Presentes no Prato: " + arr[4]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bebida1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String[] arr = getInfoBebida(email, 0);
                    JOptionPane.showMessageDialog(mainFrame, "Info da Bebida \n" + "Nome da Bebida: " + arr[0] + "\n" + "Detalhes da Bebida: " + arr[1] + "\n"
                            + "Preço da Bebida: " + arr[2] + "€" + "\n" + "Capacidade da Bebida: "+ arr[3] + "CL");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        prato2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String[] arr = getInfoPrato(email, 1);
                    JOptionPane.showMessageDialog(mainFrame, "Info do Prato \n" + "Nome do Prato: " + arr[0] + "\n" + "Detalhes do Prato: " + arr[1] + "\n"
                            + "Preço do Prato: " + arr[2] + "€" + "\n" + "Tipo de Prato: "+ arr[3] + "\n" + "Alergenicos Presentes no Prato: " + arr[4]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bebida2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String[] arr = getInfoBebida(email, 1);
                    JOptionPane.showMessageDialog(mainFrame, "Info da Bebida \n" + "Nome da Bebida: " + arr[0] + "\n" + "Detalhes da Bebida: " + arr[1] + "\n"
                            + "Preço da Bebida: " + arr[2] + "€" + "\n" + "Capacidade da Bebida: "+ arr[3] + "CL");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        prato3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String[] arr = getInfoPrato(email, 2);
                    JOptionPane.showMessageDialog(mainFrame, "Info do Prato \n" + "Nome do Prato: " + arr[0] + "\n" + "Detalhes do Prato: " + arr[1] + "\n"
                            + "Preço do Prato: " + arr[2] + "€" + "\n" + "Tipo de Prato: "+ arr[3] + "\n" + "Alergenicos Presentes no Prato: " + arr[4]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bebida3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String[] arr = getInfoBebida(email, 2);
                    JOptionPane.showMessageDialog(mainFrame, "Info da Bebida \n" + "Nome da Bebida: " + arr[0] + "\n" + "Detalhes da Bebida: " + arr[1] + "\n"
                            + "Preço da Bebida: " + arr[2] + "€" + "\n" + "Capacidade da Bebida: "+ arr[3] + "CL");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
        //menu cardapio
        menu1.setBorder(border);
        menu2.setBorder(border);
        menu3.setBorder(border);

    }

    public Icon resizeImage(Image image, JLabel restauranteImage) {
        Image resizedimg = image.getScaledInstance(restauranteImage.getWidth(), restauranteImage.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(resizedimg);
    }

    public Icon ImageSize(Image image) {
        Image pratoImage = image.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        return new ImageIcon(pratoImage);
    }


    private void adicionarPratodeSQL(Restaurante restaurante) throws SQLException {
        Prato[] pratos = Prato.from(restaurante);

        for (int i = 0; i < pratos.length; i++) {
            JLabel[] pratosVariaveis = {prato1, prato3, prato2};
            String tipoprato = String.valueOf(pratos[i].getNome());
            pratosVariaveis[i].setText(tipoprato);

            Image img = pratos[i].getImagem();
            Icon icon = ImageSize(img);

            pratosVariaveis[i].setIcon(icon);
        }

    }
    //TODO: da para otimizar estas funções so numa, mas para efeitos de teste (so adicionar pratos, ou so adicionar bebidas), fica por agora assim
    //TODO: otimizar se pratos/bebidas image for null
    //TODO: help button com dialogue box

    private void adicionarBebidadeSQL(Restaurante restaurante) throws SQLException {
        Bebida[] bebidas = Bebida.from(restaurante);

        for (int i = 0; i < bebidas.length; i++) {
            JLabel[] pratosVariaveis = {bebida1, bebida2, bebida3};
            String tipoprato = String.valueOf(bebidas[i].getNome());
            pratosVariaveis[i].setText(tipoprato);

            Image img = bebidas[i].getImagem();
            //if null setup X, else setup img
            Icon icon = ImageSize(img);

            pratosVariaveis[i].setIcon(icon);
        }

    }

    private String[] getInfoPrato(String email, int i) throws SQLException {
        Restaurante rest = Restaurante.getRestaurante(email);
        Prato[] pratos = Prato.from(rest);
        String nomeprato = pratos[i].getNome();
        String detalhesprato = pratos[i].getDetalhes();
        String precoprato = String.valueOf(pratos[i].getPrecoUnitario());
        String tipoprato = String.valueOf(pratos[i].getTipoPrato());
        String alergenio = pratos[i].getAlergenios();
        return new String[]{nomeprato, detalhesprato, precoprato, tipoprato, alergenio};
    }

    private String[] getInfoBebida(String email, int i) throws SQLException {
        Restaurante rest = Restaurante.getRestaurante(email);
        Bebida[] bebidas = Bebida.from(rest);
        String nomebebidas = bebidas[i].getNome();
        String detalhesbebidas = bebidas[i].getDetalhes();
        String precobebidas = String.valueOf(bebidas[i].getPrecoUnitario());
        String capacidadebebidas = String.valueOf(bebidas[i].getCapacidadeCL());
        return new String[]{nomebebidas, detalhesbebidas, precobebidas, capacidadebebidas};
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
        prato3 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        prato2 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        bebida1 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        bebida2 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        bebida3 = new JLabel(new ImageIcon("src\\imageresources\\food-tray.png"));
        helpIcon = new JLabel(new ImageIcon("src\\imageresources\\question.png"));

    }
}
