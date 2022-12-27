package orderngo.userinterface;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GestorForm {
    private JButton GESTORTESTEButton;
    private JPanel gestorPanel;

    public GestorForm(JFrame parent) {
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Order-N-Go Main");
        mainFrame.setContentPane(gestorPanel);
        mainFrame.setMinimumSize(new Dimension(750, 750));
        mainFrame.setLocationRelativeTo(parent);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
