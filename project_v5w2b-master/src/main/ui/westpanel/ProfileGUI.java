package ui.westpanel;

import javax.swing.*;
import java.awt.*;

public class ProfileGUI {

    private JPanel panel;
    private GridBagConstraints constraints;

    public ProfileGUI() {
        panel = new JPanel();
        constraints = new GridBagConstraints();
        initGraphics();
    }

    private void initGraphics() {
        panel.setLayout(new GridBagLayout());
    }

}
