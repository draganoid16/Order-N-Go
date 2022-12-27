package orderngo.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MainForm {

    private JLabel usernameCustom;
    private JLabel restauranteLabel;
    private JLabel cardapioLabel;
    private JLabel infoLabel;
    private JPanel mainPanel;
    private JLabel userImage;

    public MainForm(JFrame parent) {



        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Order-N-Go Main");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setMinimumSize(new Dimension(750, 750));
        mainFrame.setLocationRelativeTo(parent);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);

        restauranteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "This is a message dialog", "Dialog", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void createUIComponents() {
        userImage = new JLabel(new ImageIcon("C:\\Users\\Joao\\Desktop\\Order-N-Go\\src\\profile(1).png"));

        // TODO usernameCustom: trocar o nome baseado no username guardado no SQL
        usernameCustom = new JLabel("Username");
        usernameCustom.setFont(new Font("Arial Black", Font.PLAIN, 14));
    }
}
