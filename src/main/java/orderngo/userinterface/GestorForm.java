package orderngo.userinterface;


import orderngo.basedados.ConectorBD;
import orderngo.utilizador.GestorOrderAndGo;
import orderngo.utilizador.Restaurante;
import orderngo.utils.ImagemUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

    public class GestorForm {
        private JPanel gestorPanel,FieldPanel,UserPanel,RestaurantePanel;
        private JLabel restauranteLabel,lbNome, lbTelemovel, lbPassword, lbEmail,lbMorada,usernameLabel,userImage,restauranteImg,labelImageRest;
        private JTextField NomeTextField,emailTextField,moradaTextField,telemovelTextField;
        private JPasswordField passwordPasswordField;
        private JButton btnAtualizar,btnEliminar,cancelarButton,btnNovo;
        private JScrollPane RestaurantField;
        private JList restauranteList;
        private JButton logoutbtn;
        private final ArrayList<String> rests = new ArrayList<>();
        private final ArrayList<String> restemails = new ArrayList<>();
        private String[] str;
        private String selectEmail;
        private BufferedImage imageRestaurante;
        private final BufferedImage[] bi = new BufferedImage[1];


        public GestorForm(JFrame parent, String email) throws SQLException {
            JFrame mainFrame = new JFrame();
            mainFrame.setTitle("Order-N-Go Gestor");
            mainFrame.setContentPane(gestorPanel);
            clearFields();
            GestorOrderAndGo gest = GestorOrderAndGo.getGestor(email);
            usernameLabel.setText(gest.getNome());
            carregaRestaurante();
            mainFrame.setMinimumSize(new Dimension(950, 750));
            mainFrame.setLocationRelativeTo(parent);
            mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            mainFrame.setVisible(true);

            btnNovo.addActionListener(new ActionListener() {
                /**
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnAtualizar.setText("Guardar");
                    restauranteList.clearSelection();
                    NomeTextField.setVisible(true);
                    lbNome.setVisible(true);
                    lbEmail.setVisible(true);
                    emailTextField.setVisible(true);
                    lbPassword.setVisible(true);
                    passwordPasswordField.setVisible(true);
                    NomeTextField.setText("");
                    moradaTextField.setText("");
                    emailTextField.setText("");
                    telemovelTextField.setText("");
                    passwordPasswordField.setText("");
                }
            });
            cancelarButton.addActionListener(new ActionListener() {
                /**
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(parent, "Deseja cancelar as operações?", "Cancelar Operações",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        clearFields();
                    }
                }
            });
            btnAtualizar.addActionListener(new ActionListener() {
                /**
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(btnAtualizar.getText().equals("Atualizar")) {
                        int result = JOptionPane.showConfirmDialog(parent, "Deseja atualizar dados do restaurante?", "Atualizar dados",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(result == JOptionPane.YES_OPTION) {
                            String telemovel = telemovelTextField.getText();
                            String morada = moradaTextField.getText();
                            try {
                                Restaurante rest = Restaurante.getRestaurante(selectEmail);
                                rest.setTelemovel(telemovel);
                                rest.setMorada(morada);
                                if(bi[0]!=null) {
                                    rest.setImagem(bi[0]);
                                }
                                rest.save();
                            }catch(SQLException sqlException){
                                throw new RuntimeException(sqlException);
                            }
                            clearFields();
                            carregaRestaurante();
                        }
                    }else if (btnAtualizar.getText().equals("Guardar")) {
                        int result = JOptionPane.showConfirmDialog(parent, "Deseja inserir dados do restaurante?", "Novo Restaurante",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION) {
                            String email = emailTextField.getText();
                            String nome = NomeTextField.getText();
                            String telemovel = telemovelTextField.getText();
                            String morada = moradaTextField.getText();
                            char[] password = passwordPasswordField.getPassword();
                            try {
                                Restaurante restaurante = new Restaurante(email,nome,telemovel,morada);
                                restaurante.setPassword(password);
                                if(bi[0]!=null) {
                                    restaurante.setImagem(bi[0]);
                                }else{
                                    restaurante.setImagem(ImagemUtils.ficheiroToImage("src//imageresources//noimagefound.jpg"));
                                }
                                restaurante.save();
                            }catch(SQLException sqlException){
                                throw new RuntimeException(sqlException);
                            }
                        }
                        clearFields();
                        carregaRestaurante();
                    }
                }
            });
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(parent, "Deseja eliminar os dados do restaurante?", "Eliminar Restaurante",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Restaurante rest = Restaurante.getRestaurante(selectEmail);
                            rest.delete();
                            clearFields();
                            carregaRestaurante();
                        } catch (SQLException sqlException) {
                            System.out.println(sqlException);
                        }
                    }
                }
            });
            restauranteList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!restauranteList.isSelectionEmpty()) {
                        selectEmail = restemails.get(restauranteList.getSelectedIndex());
                        try {
                            Restaurante rest = Restaurante.getRestaurante(selectEmail);
                            moradaTextField.setText(rest.getMorada());
                            telemovelTextField.setText(rest.getTelemovel());
                            BufferedImage imagem = rest.getImagem();
                            Image resizedimg;
                            if (imagem != null) {
                                resizedimg = imagem.getScaledInstance(160,160,Image.SCALE_SMOOTH);
                            }else {
                                imagem = ImagemUtils.ficheiroToImage("src//imageresources//noimagefound.jpg");
                                resizedimg = imagem.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
                            }
                            Icon icon = new ImageIcon(resizedimg);
                            restauranteImg.setIcon(icon);
                        } catch (SQLException sqlException) {
                            System.out.println(sqlException);
                        }
                    }
                }
            });
            restauranteImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    bi[0] = escolherImagem();
                    Image resizedimg = bi[0].getScaledInstance(160, 160, Image.SCALE_SMOOTH);
                    Icon icon = new ImageIcon(resizedimg);
                    restauranteImg.setIcon(icon);
                }
            });
            logoutbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(parent, "Deseja fazer logout?", "Logout",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        LoginForm loginForm = new LoginForm(null);
                        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                        JOptionPane.showMessageDialog(parent, "Logout com sucesso!", "Logout com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
        }
        public BufferedImage escolherImagem(){
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Escolha a Imagem");
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("All images", "jpg");
            file.addChoosableFileFilter(filter);
            file.setFileFilter(filter);
            BufferedImage bi;
            int result = file.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedfile = file.getSelectedFile();
                try {
                    bi = ImageIO.read(selectedfile);
                    return bi;
                }catch(IOException e){
                    e.getStackTrace();
                }
            }
            return null;
        }

        private void carregaRestaurante() {
            newModel NwModl = new newModel();
            newRenderer NwRndrer = new newRenderer();
            restauranteList.setModel(NwModl);
            restauranteList.setCellRenderer(NwRndrer);
            restauranteList.setVisibleRowCount(1);
            restauranteList.setFixedCellWidth(230);
            restauranteList.setFixedCellHeight(200);
            restauranteList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        }

        class newModel extends DefaultListModel
        {
            public newModel()
            {
                rests.clear();
                restemails.clear();
                removeAllElements();
                try {
                    var cbd = ConectorBD.getInstance();
                    var ps = cbd.prepareStatement("SELECT * FROM restaurante WHERE visivel = true");
                    try (ResultSet result = cbd.executePreparedQuery(ps)) {
                        while (result.next()) {
                            rests.add(result.getString("nome"));
                            restemails.add(result.getString("email"));
                            BufferedImage imagem = ImagemUtils.blobToImage(result.getBlob("imagem"));
                            if (imagem != null) {
                                Image resizedimg = imagem.getScaledInstance(160,160,Image.SCALE_SMOOTH);
                                addElement(new Object[]{result.getString("nome"), new ImageIcon(resizedimg)});
                            }else{
                                BufferedImage image = ImagemUtils.ficheiroToImage("src//imageresources//noimagefound.jpg");
                                Image resizedimg = image.getScaledInstance(160,160,Image.SCALE_SMOOTH);
                                addElement(new Object[]{result.getString("nome"), new ImageIcon(resizedimg)});

                            }
                        }
                    }
                    str= new String[rests.size()];
                    for (int i = 0; i < rests.size(); i++) {
                        str[i] = rests.get(i);
                    }
                }catch(SQLException sqlException){
                    System.out.println(sqlException);
                }
            }
        }
        class newRenderer extends JLabel implements ListCellRenderer
        {
            public newRenderer()
            {
                setOpaque(true);
            }
            public Component getListCellRendererComponent(JList JLst, Object ob1, int indx, boolean isSelected,boolean Focus)
            {
                newModel Mdl = (newModel)JLst.getModel();
                setText((String)((Object[])ob1)[0]);
                setIcon((Icon)((Object[])ob1)[1]);
                if(!isSelected)
                {
                    setBackground(JLst.getBackground());
                    setForeground(JLst.getForeground());
                }
                else
                {
                    setBackground(JLst.getSelectionBackground());
                    setForeground(JLst.getSelectionForeground());
                }
                return this;
            }
        }
        public static BufferedImage getBufferedImageFromIcon(Icon icon) {
            BufferedImage buffer = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics g = buffer.getGraphics();
            icon.paintIcon(new JLabel(), g, 0, 0);
            g.dispose();
            return buffer;
        }

        public void clearFields(){
            BufferedImage imagem = ImagemUtils.ficheiroToImage("src//imageresources//selectImage.jpg");
            Image resizedimg = imagem.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            Icon icon = new ImageIcon(resizedimg);
            restauranteImg.setIcon(icon);
            NomeTextField.setText("");

            emailTextField.setText("");
            moradaTextField.setText("");
            telemovelTextField.setText("");
            passwordPasswordField.setText("");
            restauranteImg.setText("");
            NomeTextField.setVisible(false);
            lbNome.setVisible(false);
            lbEmail.setVisible(false);
            emailTextField.setVisible(false);
            lbPassword.setVisible(false);
            passwordPasswordField.setVisible(false);
            restauranteList.clearSelection();
        }
        private void createUIComponents() {
            // TODO: place custom component creation code here
        }
    }
