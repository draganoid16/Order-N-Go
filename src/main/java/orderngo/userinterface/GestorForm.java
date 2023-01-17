package orderngo.userinterface;


import orderngo.basedados.ConectorBD;
import orderngo.utilizador.Utilizador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

    public class GestorForm {
        private JPanel gestorPanel,FieldPanel,UserPanel,RestaurantePanel;
        private JLabel restauranteLabel,lbNome, lbTelemovel, lbPassword, lbEmail,lbMorada,usernameLabel,userImage;
        private JTextField NomeTextField,emailTextField,moradaTextField,telemovelTextField;
        private JPasswordField passwordPasswordField;
        private JButton btnAtualizar,btnEliminar,cancelarButton,btnNovo;
        private JScrollPane RestaurantField;
        private JList restauranteList;
        private final ArrayList<String> rests = new ArrayList<>();
        private final ArrayList<String> restemails = new ArrayList<>();
        private String[] str;
        private String selectEmail;

        public GestorForm(JFrame parent, String email) throws SQLException {
            JFrame mainFrame = new JFrame();
            mainFrame.setTitle("Order-N-Go Gestor");
            mainFrame.setContentPane(gestorPanel);
            carregaRestaurante();
            mainFrame.setMinimumSize(new Dimension(1050, 1050));
            mainFrame.setLocationRelativeTo(parent);
            mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            mainFrame.setVisible(true);

            btnNovo.addActionListener(new ActionListener() {
                /**
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    restauranteList.clearSelection();
                    NomeTextField.setVisible(true);
                    lbNome.setVisible(true);
                    lbEmail.setVisible(true);
                    emailTextField.setVisible(true);
                    lbPassword.setVisible(true);
                    passwordPasswordField.setVisible(true);
                    btnAtualizar.setText("Guardar");
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
                        NomeTextField.setText("");
                        emailTextField.setText("");
                        moradaTextField.setText("");
                        telemovelTextField.setText("");
                        passwordPasswordField.setText("");
                        NomeTextField.setVisible(false);
                        lbNome.setVisible(false);
                        lbEmail.setVisible(false);
                        emailTextField.setVisible(false);
                        lbPassword.setVisible(false);
                        passwordPasswordField.setVisible(false);
                        restauranteList.clearSelection();
                    }
                }
            });
            btnAtualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (btnAtualizar.getText().equals("Atualizar")) {
                        int result = JOptionPane.showConfirmDialog(parent, "Deseja atualizar dados do restaurante?", "Atualizar dados",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION) {
                            String nome = str[restauranteList.getSelectedIndex()];
                            String telemovel = telemovelTextField.getText();
                            String morada = moradaTextField.getText();
                            Utilizador utilizador = new Utilizador(selectEmail,nome,telemovel,morada) {
                                @Override
                                public void delete() throws SQLException {

                                }

                                @Override
                                public void save() throws SQLException {

                                }
                            };
                                try {
                                    var cbd = ConectorBD.getInstance();
                                    var ps = cbd.prepareStatement("UPDATE restaurante SET telemovel = ?, morada = ? ,visivel = true WHERE email = ?");
                                    ps.setString(1, utilizador.getTelemovel());
                                    ps.setString(2, utilizador.getMorada());
                                    //ps.setBlob(3, ImagemUtils.imageToInputStream(imagem));
                                    //ps.setString(4, getPasswordEncriptada());
                                    ps.setString(3, selectEmail);
                                    cbd.executePreparedUpdate(ps);
                                } catch (SQLException sqlException) {
                                    System.out.println(sqlException);
                                }
                                telemovelTextField.setText("");
                                moradaTextField.setText("");
                                restauranteList.clearSelection();
                        }
                    } else if (btnAtualizar.getText().equals("Guardar")) {
                        int result = JOptionPane.showConfirmDialog(parent, "Deseja inserir dados do restaurante?", "Novo Restaurante",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if (result == JOptionPane.YES_OPTION) {
                            String email = emailTextField.getText();
                            String nome = NomeTextField.getText();
                            String telemovel = telemovelTextField.getText();
                            String morada = moradaTextField.getText();
                            Utilizador utilizador = new Utilizador(email,nome,telemovel,morada) {
                                @Override
                                public void delete() throws SQLException {

                                }

                                @Override
                                public void save() throws SQLException {

                                }
                            };
                            utilizador.setPassword(passwordPasswordField.getPassword());
                            try {
                                var cbd = ConectorBD.getInstance();
                                var ps = cbd.prepareStatement("INSERT INTO restaurante(email, nome, telemovel, morada, palavraPasse) VALUES (?, ?, ?, ?, ?)");
                                ps.setString(1, utilizador.getEmail());
                                ps.setString(2, utilizador.getNome());
                                ps.setString(3, utilizador.getTelemovel());
                                ps.setString(4, utilizador.getMorada());
                                //ps.setBlob(5, ImagemUtils.imageToInputStream(imagem));
                                ps.setString(5, utilizador.getPasswordEncriptada());

                                cbd.executePreparedUpdate(ps);
                                System.out.println(utilizador.getPasswordEncriptada());
                                telemovelTextField.setText("");
                                moradaTextField.setText("");
                                restauranteList.clearSelection();
                            }catch(SQLException sqlException){
                                System.out.println(sqlException);
                            }
                        }
                        NomeTextField.setVisible(false);
                        lbNome.setVisible(false);
                        lbEmail.setVisible(false);
                        emailTextField.setVisible(false);
                        lbPassword.setVisible(false);
                        passwordPasswordField.setVisible(false);
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
                            var cbd = ConectorBD.getInstance();
                            var ps = cbd.prepareStatement("DELETE FROM restaurante where email=?");
                            ps.setString(1, selectEmail);

                            cbd.executePreparedUpdate(ps);
                            telemovelTextField.setText("");
                            moradaTextField.setText("");
                            restauranteList.clearSelection();
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
                            var cbd = ConectorBD.getInstance();
                            var ps = cbd.prepareStatement("SELECT * FROM restaurante WHERE email=? AND visivel = true");

                            ps.setString(1, restemails.get(restauranteList.getSelectedIndex()));
                            try (ResultSet result = cbd.executePreparedQuery(ps)) {
                                while (result.next()) {
                                    moradaTextField.setText(result.getString("morada"));
                                    telemovelTextField.setText((result.getString("telemovel")));
                                }
                            }
                        } catch (SQLException sqlException) {
                            System.out.println(sqlException);
                        }
                    }
                }
            });
        }

        private void carregaRestaurante() {
            newModel NwModl = new newModel();
            newRenderer NwRndrer = new newRenderer();
            restauranteList.setModel(NwModl);
            restauranteList.setCellRenderer(NwRndrer);
            restauranteList.setVisibleRowCount(1);
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

                        }
                    }
                    str= new String[rests.size()];
                    for (int i = 0; i < rests.size(); i++) {
                        str[i] = rests.get(i);
                    }
                    for (int i = 0; i < str.length; i++) {
                        //Image image = new image();

                        //Image restauranteImage = image.getScaledInstance(350, 250, Image.SCALE_DEFAULT);
                        addElement(new Object[] {str[i], new ImageIcon("src//imageresources//restauranteimageexample.jpg")});
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
        private void createUIComponents() {
            // TODO: place custom component creation code here
        }
    }
