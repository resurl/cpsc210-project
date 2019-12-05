package ui.westpanel;

import network.ApiManager;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImagePanel {

    JLabel label;

    public ImagePanel(String imgExt) throws IOException {
        super();
        Image image = ApiManager.getInstance().loadImage(imgExt);
        label = new JLabel();
        label.setIcon(new ImageIcon(image));
    }

    public JLabel getLabel() {
        return label;
    }


}
