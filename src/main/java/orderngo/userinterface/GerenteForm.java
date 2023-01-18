package orderngo.userinterface;

import orderngo.cardapio.Bebida;
import orderngo.cardapio.Prato;
import orderngo.pedido.Pedido;
import orderngo.utilizador.Restaurante;
import orderngo.utilizador.Utilizador;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
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
    private JButton alterarCardapioButton;
    private JButton verPedidosButton;
    private JPanel editarBebidaPanel;
    private JTextField detalhesbebidaField;
    private JTextField precobebidaField;
    private JTextField capacidadebebidaField;
    private JTextField detalhespratoField;
    private JTextField precopratoField;
    private JTextField tipopratoField;
    private JTextField alergiaspratoField;
    private JButton OKButtonPrato;
    private JPanel editarPratoPanel;
    private JButton OKBebidaButton;
    private JButton adicionarNoCardapioButton;
    private JButton verListaCompletaButton;
    private JComboBox comboBoxTipoPrato;
    private JButton escolherImagemButton;
    private JButton escolherImagemBebida;
    private JButton removerDoCardapioButton;
    private JLabel nomedoPratoLabel;
    private JTextField nomedoPratoTextField;
    private JLabel helpDescriptionPrato;
    private JLabel nomedabebidaLabel;
    private JTextField nomedaBebidaTextField;
    private JLabel helpDescriptionBebida;
    private JPanel restauranteHolderPanel;
    private JLabel joaocoelhoimage;
    private JLabel marciotavaresimage;
    private JLabel linkedinJoao;
    private JLabel githubMarcio;
    private JPanel contactoPanel;
    private JButton ENVIAREMAILButton;
    private JLabel ruivieiraImage;
    private JLabel sergioferreiraImage;
    private JLabel gustavovitorinoImage;
    private JLabel githubRui;
    private JLabel githubSergio;

    private JLabel helpIcon;

    private JLabel restauranteEmail;

    /**
     * Construtor do GerenteForm, define todos os parametros necessarios
     * @param parent
     * @param email
     * @throws SQLException
     * @throws IOException
     */
    public GerenteForm(JFrame parent, String email) throws SQLException, IOException {


        //setup basico
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Order-N-Go Main");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setMinimumSize(new Dimension(1050, 1050));
        mainFrame.setLocationRelativeTo(parent);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
        //setup detalhado
        Restaurante restaurante = Restaurante.getRestaurante(email);
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
                            + "Preço do Prato: " + arr[2] + "€" + "\n" + "Tipo de Prato: " + arr[3] + "\n" + "Alergenicos Presentes no Prato: " + arr[4]);
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
                            + "Preço da Bebida: " + arr[2] + "€" + "\n" + "Capacidade da Bebida: " + arr[3] + "CL");
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
                            + "Preço do Prato: " + arr[2] + "€" + "\n" + "Tipo de Prato: " + arr[3] + "\n" + "Alergenicos Presentes no Prato: " + arr[4]);
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
                            + "Preço da Bebida: " + arr[2] + "€" + "\n" + "Capacidade da Bebida: " + arr[3] + "CL");
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
                            + "Preço do Prato: " + arr[2] + "€" + "\n" + "Tipo de Prato: " + arr[3] + "\n" + "Alergenicos Presentes no Prato: " + arr[4]);
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
                            + "Preço da Bebida: " + arr[2] + "€" + "\n" + "Capacidade da Bebida: " + arr[3] + "CL");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         *
         */
        alterarCardapioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    String[] arrayPrato = {};
                    String[] arrayBebida = {};
                    JComboBox jcb = new JComboBox();
                    int countPratos = adicionarPratodeSQL(Restaurante.getRestaurante(email));
                    for (int i = 1; i <= countPratos; i++) {
                        jcb.addItem(i);
                    }

                    JOptionPane.showMessageDialog(mainFrame, jcb, "Selecione o menu a alterar", JOptionPane.QUESTION_MESSAGE);
                    int escolha = (Integer) jcb.getSelectedItem();
                    JComboBox jcb2 = new JComboBox();
                    arrayPrato = getInfoPrato(email, escolha - 1);
                    arrayBebida = getInfoBebida(email, escolha - 1);


                    jcb2.addItem("Prato");
                    jcb2.addItem("Bebida");
                    JOptionPane.showMessageDialog(mainFrame, jcb2, "Quer alterar o prato ou a bebida?", JOptionPane.QUESTION_MESSAGE);


                    String escolha2 = (String) jcb2.getSelectedItem();

                    switch (escolha2) {
                        case "Prato" -> {

                            cardapioPanel.setVisible(false);
                            JComboBox jcb3 = new JComboBox();
                            //se so tiver bebida e não prato
                            if (arrayPrato.length == 0) {
                                JOptionPane.showMessageDialog(mainFrame, "", "Não existe prato para esse menu", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            editarPratoPanel.setVisible(true);
                            final BufferedImage[] bi = new BufferedImage[1];
                            escolherImagemButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    bi[0] = escolherImagem();
                                }
                            });
                            comboBoxTipoPrato.setModel(new DefaultComboBoxModel(Prato.TipoPrato.values()));
                            try {
                                detalhespratoField.setText(arrayPrato[1]);
                                precopratoField.setText(arrayPrato[2]);
                                comboBoxTipoPrato.setSelectedItem(arrayPrato[3]);
                                alergiaspratoField.setText(arrayPrato[4]);
                            } catch (NullPointerException ex) {
                                ex.printStackTrace();
                            }
                            OKButtonPrato.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        String[] arrayPratoaVerificar = getInfoPrato(email, escolha - 1);
                                        Restaurante rest = Restaurante.getRestaurante(email);
                                        float preco = Float.parseFloat(precopratoField.getText());
                                        Prato.TipoPrato tipoprato = (Prato.TipoPrato) comboBoxTipoPrato.getSelectedItem();
                                        Prato prato = new Prato(rest, arrayPratoaVerificar[0], detalhespratoField.getText(), preco, tipoprato, alergiaspratoField.getText());

                                        if (bi[0] != null) {
                                            prato.setImagem(bi[0]);
                                        }

                                        prato.save();
                                        adicionarPratodeSQL(rest);

                                    } catch (SQLException | IOException ex) {
                                        throw new RuntimeException(ex);
                                    }


                                    JOptionPane.showMessageDialog(mainFrame, "", "Prato atualizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                                    editarPratoPanel.setVisible(false);
                                    cardapioPanel.setVisible(true);
                                }
                            });
                        }

                        case "Bebida" -> {
                            cardapioPanel.setVisible(false);
                            JComboBox jcb4 = new JComboBox();
                            //se so tiver prato e não bebida (testar com r3@r3.r3)
                            if (arrayBebida.length == 0) {
                                JOptionPane.showMessageDialog(mainFrame, "", "Não existe bebida para esse menu", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            try {
                                detalhesbebidaField.setText(arrayBebida[1]);
                                precobebidaField.setText(arrayBebida[2]);
                                capacidadebebidaField.setText(arrayBebida[3]);

                            } catch (NullPointerException ex) {
                                ex.printStackTrace();
                            }
                            editarBebidaPanel.setVisible(true);
                            final BufferedImage[] bi = new BufferedImage[1];
                            escolherImagemBebida.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    bi[0] = escolherImagem();
                                }
                            });
                            OKBebidaButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        String[] arrayBebidaaVerificar = getInfoBebida(email, escolha - 1);
                                        Restaurante rest = Restaurante.getRestaurante(email);
                                        float preco = Float.parseFloat(precobebidaField.getText());
                                        int capacidade = Integer.parseInt(capacidadebebidaField.getText());

                                        Bebida bebida = new Bebida(rest, arrayBebidaaVerificar[0], detalhesbebidaField.getText(), preco, capacidade);

                                        if (bi[0] != null) {
                                            bebida.setImagem(bi[0]);
                                        }

                                        bebida.save();
                                        adicionarBebidadeSQL(rest);


                                    } catch (SQLException | IOException ex) {
                                        throw new RuntimeException(ex);
                                    }


                                    JOptionPane.showMessageDialog(mainFrame, "", "Bebida atualizada com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                                    editarBebidaPanel.setVisible(false);
                                    cardapioPanel.setVisible(true);
                                }
                            });
                        }

                    }


                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        adicionarNoCardapioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JComboBox jcb = new JComboBox();


                jcb.addItem("Prato");
                jcb.addItem("Bebida");
                JOptionPane.showMessageDialog(mainFrame, jcb, "Quer adicionar um prato ou uma bebida?", JOptionPane.QUESTION_MESSAGE);


                String escolha = (String) jcb.getSelectedItem();

                switch (escolha) {
                    case "Prato" -> {
                        helpDescriptionPrato.setText("Para adicionar o seu prato, introduza os detalhes abaixo e carregue em OK");
                        nomedoPratoLabel.setVisible(true);
                        nomedoPratoTextField.setVisible(true);
                        comboBoxTipoPrato.setModel(new DefaultComboBoxModel(Prato.TipoPrato.values()));
                        cardapioPanel.setVisible(false);
                        editarPratoPanel.setVisible(true);
                        final BufferedImage[] bi = new BufferedImage[1];
                        escolherImagemButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                bi[0] = escolherImagem();
                            }
                        });

                        OKButtonPrato.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                try {
                                    Restaurante rest = Restaurante.getRestaurante(email);
                                    float preco = Float.parseFloat(precopratoField.getText());
                                    Prato.TipoPrato tipoprato = (Prato.TipoPrato) comboBoxTipoPrato.getSelectedItem();
                                    Prato prato = new Prato(rest, nomedoPratoTextField.getText(), detalhespratoField.getText(), preco, tipoprato, alergiaspratoField.getText());

                                    if (bi[0] != null) {
                                        prato.setImagem(bi[0]);
                                    }

                                    prato.save();
                                    adicionarPratodeSQL(rest);

                                } catch (SQLException | IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                JOptionPane.showMessageDialog(mainFrame, "", "Prato adicionado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                                nomedoPratoLabel.setVisible(false);
                                nomedoPratoTextField.setVisible(false);
                                editarPratoPanel.setVisible(false);
                                cardapioPanel.setVisible(true);
                            }
                        });
                    }

                    case "Bebida" -> {
                        helpDescriptionBebida.setText("Para adicionar a sua bebida, introduza os detalhes abaixo e carregue em OK");
                        nomedabebidaLabel.setVisible(true);
                        nomedaBebidaTextField.setVisible(true);
                        cardapioPanel.setVisible(false);
                        editarBebidaPanel.setVisible(true);
                        final BufferedImage[] bi = new BufferedImage[1];
                        escolherImagemBebida.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                bi[0] = escolherImagem();
                            }
                        });

                        OKBebidaButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                try {
                                    Restaurante rest = Restaurante.getRestaurante(email);
                                    float preco = Float.parseFloat(precobebidaField.getText());
                                    int capacidade = Integer.parseInt(capacidadebebidaField.getText());

                                    Bebida bebida = new Bebida(rest, nomedaBebidaTextField.getText(), detalhesbebidaField.getText(), preco, capacidade);

                                    if (bi[0] != null) {
                                        bebida.setImagem(bi[0]);
                                    }

                                    bebida.save();
                                    adicionarBebidadeSQL(rest);


                                } catch (SQLException | IOException ex) {
                                    throw new RuntimeException(ex);
                                }


                                JOptionPane.showMessageDialog(mainFrame, "", "Bebida adicionada com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                                nomedabebidaLabel.setVisible(false);
                                nomedaBebidaTextField.setVisible(false);
                                editarBebidaPanel.setVisible(false);
                                cardapioPanel.setVisible(true);
                            }
                        });
                    }
                }


            }
        });
        removerDoCardapioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JComboBox jcb = new JComboBox();


                jcb.addItem("Prato");
                jcb.addItem("Bebida");
                JOptionPane.showMessageDialog(mainFrame, jcb, "Quer remover um prato ou uma bebida?", JOptionPane.QUESTION_MESSAGE);


                String escolha = (String) jcb.getSelectedItem();

                switch (escolha) {

                    case "Prato" -> {
                        try {
                            JComboBox jcb2 = new JComboBox();
                            Restaurante rest = Restaurante.getRestaurante(email);
                            Prato[] allPratos = Prato.from(rest);
                            for (int i = 0; i < allPratos.length; i++) {
                                jcb2.addItem(allPratos[i].getNome());
                            }
                            if (jcb2 != null) {
                                JOptionPane.showMessageDialog(mainFrame, jcb2, "Escolha o prato a remover", JOptionPane.QUESTION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(mainFrame, jcb2, "Não existem pratos!", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            String escolha2 = (String) jcb2.getSelectedItem();
                            Prato prato = new Prato(rest, escolha2, "null", 10, Prato.TipoPrato.PEIXE, "null");
                            prato.delete();

                            JOptionPane.showMessageDialog(mainFrame, "", "Prato removido com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                            adicionarPratodeSQL(rest);

                        } catch (SQLException | IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                    case "Bebida" -> {
                        try {
                            JComboBox jcb2 = new JComboBox();
                            Restaurante rest = Restaurante.getRestaurante(email);
                            Bebida[] allBebidas = Bebida.from(rest);
                            for (int i = 0; i < allBebidas.length; i++) {
                                jcb2.addItem(allBebidas[i].getNome());
                            }
                            if (jcb2 != null) {
                                JOptionPane.showMessageDialog(mainFrame, jcb2, "Escolha a bebida a remover", JOptionPane.QUESTION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(mainFrame, jcb2, "Não existem bebidas!", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            String escolha2 = (String) jcb2.getSelectedItem();
                            Bebida bebida = new Bebida(rest, escolha2, "null", 10, 10);
                            bebida.delete();

                            JOptionPane.showMessageDialog(mainFrame, "", "Bebida removida com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                            adicionarBebidadeSQL(rest);

                        } catch (SQLException | IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            }
        });
        verPedidosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JComboBox jcb = new JComboBox();

                try {
                    Pedido[] pedido = Pedido.from(restaurante);
                    for(int i=0; i< pedido.length;i++){
                        Pedido ped = Pedido.getPedido(i+1);
                        ped.fill();
                        jcb.addItem(pedido[i].getCliente());
                        jcb.addItem("Numero de Pedido: " + pedido[i].getNrPedido() + " Morada de Entrega: " + pedido[i].getMoradaEntrega());
                        jcb.addItem("Items do Pedido: " + ped.getItemsPedido());
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(mainFrame, jcb, "Pedidos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        verListaCompletaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JComboBox jcb = new JComboBox();
                try {
                    Prato[] allPratos = Prato.from(restaurante);
                    Bebida[] allBebidas = Bebida.from(restaurante);
                    jcb.addItem("--PRATOS--");
                    for(int i=0; i< allPratos.length;i++){
                        jcb.addItem(" Nome: " + allPratos[i].getNome() + " Detalhes: " + allPratos[i].getDetalhes() + " Preço: " + allPratos[i].getPrecoUnitario() + " Tipo de Prato: " + allPratos[i].getTipoPrato() + " Alergias: " + allPratos[i].getAlergenios());
                    }
                    jcb.addItem("--BEBIDAS--");
                    for(int i=0; i< allBebidas.length;i++){
                        jcb.addItem(" Nome: " + allBebidas[i].getNome() + " Detalhes: " + allBebidas[i].getDetalhes() + " Preço: " + allBebidas[i].getPrecoUnitario() + " Capacidade: " + allBebidas[i].getCapacidadeCL());
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(mainFrame, jcb, "Todos os Pratos e Bebidas", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        linkedinJoao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI(linkedinJoao.getText().trim());
                    desktop.browse(oURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                linkedinJoao.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                linkedinJoao.setForeground(Color.WHITE);
            }
        });
        githubMarcio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI(githubMarcio.getText().trim());
                    desktop.browse(oURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                githubMarcio.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                githubMarcio.setForeground(Color.WHITE);
            }
        });
        ENVIAREMAILButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Desktop desktop = null;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }

                try {
                    desktop.mail();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    githubRui.addKeyListener(new KeyAdapter() { } );
        githubRui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI(githubRui.getText().trim());
                    desktop.browse(oURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                githubRui.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                githubRui.setForeground(Color.WHITE);
            }
        });
        githubSergio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI(githubSergio.getText().trim());
                    desktop.browse(oURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                githubSergio.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                githubSergio.setForeground(Color.WHITE);
            }
        });
    }

    /**
     * Setup inicial do RestaurantePanel, define o texto e imagem da BD
     * @param email
     * @throws SQLException
     */
    private void restauranteSetup(String email) throws SQLException {
        //panels comecam não visiveis e ficam visiveis on click no menu
        restaurantePanel.setVisible(false);
        cardapioPanel.setVisible(false);
        infoPanel.setVisible(false);
        //Restaurante Setup
        Border border = BorderFactory.createLineBorder(Color.white);
        Restaurante restaurante = Restaurante.getRestaurante(email);
        Utilizador utilizador = Restaurante.getRestaurante(email);
        usernameCustom.setText(utilizador.getNome());
        restauranteName.setText(utilizador.getNome());
        restauranteName.setBorder(border);
        emailLabel.setText(utilizador.getEmail());
        moradaLabel.setText(utilizador.getMorada());
        telemovelLabel.setText(utilizador.getTelemovel());
        //Setup imagem
        Image img = restaurante.getImagem();
        if (img != null) {
            Icon icon = resizeImage(img, restauranteImage);
            restauranteImage.setIcon(icon);
        }
        restauranteImage.setBorder(border);
        //menu cardapio
        menu1.setBorder(border);
        menu2.setBorder(border);
        menu3.setBorder(border);

        contactoPanel.setBorder(border);

    }

    /**
     * Função para dar resize a uma imagem, baseado no tamanho do Jlabel em que ela está presente
     * @param image
     * @param restauranteImage
     * @return
     */
    public Icon resizeImage(Image image, JLabel restauranteImage) {
        Image resizedimg = image.getScaledInstance(restauranteImage.getWidth(), restauranteImage.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(resizedimg);
    }

    /**
     * Função para dar resize a uma imagem baseado em parametros pre-definidos
     * @param image
     * @param widght
     * @param height
     * @return
     */
    public Icon CardapioImageSize(Image image, int widght, int height) {
        Image pratoImage = image.getScaledInstance(widght, height, Image.SCALE_DEFAULT);
        return new ImageIcon(pratoImage); //350 250
    }

    /**
     * Função para escolher uma image do Sistema Operativo, e retornar-la.
     * @return
     */
    public BufferedImage escolherImagem() {
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Escolha a Imagem");
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        BufferedImage bi;
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedfile = file.getSelectedFile();
            try {
                bi = ImageIO.read(selectedfile);
                JOptionPane.showMessageDialog(menuPanel, "", "Imagem Selecionada", JOptionPane.INFORMATION_MESSAGE);
                return bi;
            } catch (IOException e) {
                e.getStackTrace();
                JOptionPane.showMessageDialog(menuPanel, "", "Erro ao selecionar imagem!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Função que define os 3 primeiros pratos do UI visual no cardapioPanel
     * @param restaurante
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ArrayIndexOutOfBoundsException
     */
    private int adicionarPratodeSQL(Restaurante restaurante) throws SQLException, IOException, ArrayIndexOutOfBoundsException {
        Prato[] pratos = Prato.from(restaurante);

        for (int i = 0; i < pratos.length; i++) {
            JLabel[] pratosVariaveis = {prato1, prato2, prato3};
            String tipoprato = String.valueOf(pratos[i].getNome());
            if(tipoprato!= null) {
                pratosVariaveis[i].setText(tipoprato);
            }else{
                pratosVariaveis[i].setText("Nome de Prato não encontrado");
            }
            Image img = pratos[i].getImagem();
            if (img != null) {
                Icon icon = CardapioImageSize(img, 350, 250);
                pratosVariaveis[i].setIcon(icon);
            }else{
            BufferedImage img2 = ImageIO.read(new File("src\\imageresources\\noimagefound.jpg"));
            Icon icon = CardapioImageSize(img2, 350, 250);
            pratosVariaveis[i].setIcon(icon);
        }

        }

        return pratos.length;
    }

    /**
     * Função que define as 3 primeiras bebidas do UI visual no cardapioPanel
     * @param restaurante
     * @throws SQLException
     * @throws IOException
     * @throws ArrayIndexOutOfBoundsException
     */
    private void adicionarBebidadeSQL(Restaurante restaurante) throws SQLException, IOException, ArrayIndexOutOfBoundsException {
        Bebida[] bebidas = Bebida.from(restaurante);

        for (int i = 0; i < bebidas.length; i++) {
            JLabel[] bebidasVariaveis = {bebida1, bebida2, bebida3};
            String tipoprato = String.valueOf(bebidas[i].getNome());
            if(tipoprato!= null) {
                bebidasVariaveis[i].setText(tipoprato);
            }else{
                bebidasVariaveis[i].setText("Nome de Bebida não encontrado");
            }
            Image img = bebidas[i].getImagem();
            //if not null setup X, else setup img
            if (img != null) {
                Icon icon = CardapioImageSize(img, 350, 250);
                bebidasVariaveis[i].setIcon(icon);
            }else{
                BufferedImage img2 = ImageIO.read(new File("src\\imageresources\\noimagefound.jpg"));
                Icon icon = CardapioImageSize(img2, 350, 250);
                bebidasVariaveis[i].setIcon(icon);
            }

        }

    }

    /**
     * Função que puxa o info do prato da BD
     * @param email
     * @param i
     * @return
     * @throws SQLException
     */
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

    /**
     * Função que puxa o info da bebida da BD
     * @param email
     * @param i
     * @return
     * @throws SQLException
     */
    private String[] getInfoBebida(String email, int i) throws SQLException {
        Restaurante rest = Restaurante.getRestaurante(email);
        Bebida[] bebidas = Bebida.from(rest);
        if (bebidas.length > 0) {
            String nomebebidas = bebidas[i].getNome();
            String detalhesbebidas = bebidas[i].getDetalhes();
            String precobebidas = String.valueOf(bebidas[i].getPrecoUnitario());
            String capacidadebebidas = String.valueOf(bebidas[i].getCapacidadeCL());
            return new String[]{nomebebidas, detalhesbebidas, precobebidas, capacidadebebidas};
        }
        return new String[]{};
    }

    /**
     * Criação de componentes JSwing custom, onde tem que se definir no codigo os atributos
     */
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
        prato1 = new JLabel(new ImageIcon("src\\imageresources\\noimagefound.jpg"));
        prato3 = new JLabel(new ImageIcon("src\\imageresources\\noimagefound.jpg"));
        prato2 = new JLabel(new ImageIcon("src\\imageresources\\noimagefound.jpg"));
        bebida1 = new JLabel(new ImageIcon("src\\imageresources\\noimagefound.jpg"));
        bebida2 = new JLabel(new ImageIcon("src\\imageresources\\noimagefound.jpg"));
        bebida3 = new JLabel(new ImageIcon("src\\imageresources\\noimagefound.jpg"));
        comboBoxTipoPrato = new JComboBox<Prato.TipoPrato>();

        //infopanel
        ImageIcon imgjoao = new ImageIcon("src\\imageresources\\joaocoelho.jpg");
        ImageIcon resizedImageJoao = (ImageIcon) CardapioImageSize(imgjoao.getImage(), 250, 250);
        ImageIcon imgmarcio = new ImageIcon("src\\imageresources\\marciotavares.jpg");
        ImageIcon resizedImageMarcio = (ImageIcon) CardapioImageSize(imgmarcio.getImage(), 250, 250);
        ImageIcon noimage = new ImageIcon("src\\imageresources\\noimagefound.jpg");
        ImageIcon resizedNoImage = (ImageIcon) CardapioImageSize(noimage.getImage(), 250, 250);


        joaocoelhoimage = new JLabel(new ImageIcon(resizedImageJoao.getImage()));
        marciotavaresimage = new JLabel(new ImageIcon(resizedImageMarcio.getImage()));
        ruivieiraImage = new JLabel(new ImageIcon(resizedNoImage.getImage()));
        sergioferreiraImage = new JLabel(new ImageIcon(resizedNoImage.getImage()));
        gustavovitorinoImage = new JLabel(new ImageIcon(resizedNoImage.getImage()));

    }
}
