package ui.westpanel;

import model.Media;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class MediaDetailsGUI {

    protected GridBagConstraints constraints;
    protected JPanel panel;
    protected Media media;

    public MediaDetailsGUI(JComponent parent, Media m) throws IOException {
        this.media = m;
        panel = new JPanel();
        constraints = new GridBagConstraints();
        initGraphics();
    }

    protected abstract void initGraphics() throws IOException;

    protected void addTitle(String name) {
        JLabel titleLabel = new JLabel(name);
        titleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        setConstraint(2,0,1,4);
        panel.add(titleLabel,constraints);
    }

    protected void setConstraint(int gridx, int gridy, int gridHeight, int gridWidth) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
    }

    protected JLabel imagePanel() throws IOException {
        ImagePanel image;
        if (media.getPosterPath() != null) {
            image = new ImagePanel(media.getPosterPath());
        } else {
            image = new ImagePanel("/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg");
        }
        setConstraint(0,0,4,1);
        return image.getLabel();
    }

    protected void addOverview() {
        String overview = media.getOverview();
        JTextArea overviewLabel = new JTextArea(overview);
        overviewLabel.setEditable(false);
        overviewLabel.setWrapStyleWord(true);
        overviewLabel.setLineWrap(true);
        overviewLabel.setBackground(UIManager.getColor("Label.background"));
        overviewLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
        setConstraint(1,1,2,5);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(overviewLabel,constraints);
    }

}
