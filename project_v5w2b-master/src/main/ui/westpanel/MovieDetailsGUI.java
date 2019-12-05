package ui.westpanel;

import model.Media;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MovieDetailsGUI extends MediaDetailsGUI {

    public MovieDetailsGUI(JComponent parent, Media media) throws IOException {
        super(parent, media);
        parent.add(panel);
    }

    @Override
    protected void initGraphics() throws IOException {
        panel.setLayout(new GridBagLayout());
        JLabel image = imagePanel();
        panel.add(image, constraints);
        addTitle(media.getTitle());
        addOverview();
    }


}
