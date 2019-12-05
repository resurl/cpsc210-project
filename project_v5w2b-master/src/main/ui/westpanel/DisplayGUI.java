package ui.westpanel;

import model.Media;
import ui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayGUI {
    private JPanel panel = new JPanel();
    private List<JLabel> media = new ArrayList<>();
    private Map<JLabel,Media> labelMediaMap;
    private JPanel parent;
    private MainGUI gui;

    public DisplayGUI(JPanel parent, MainGUI gui) {
        this.parent = parent;
        this.gui = gui;
        panel.setBackground(new Color(165,165,165));
        parent.add(panel);
    }

    public DisplayGUI(JPanel parent, List<? extends Media> searchResults, MainGUI gui) throws IOException {
        this.parent = parent;
        this.gui = gui;
        this.labelMediaMap = new HashMap<>();
        initGraphics();
        generateEntries(searchResults);
    }

    private void initGraphics() {
        GridLayout grid = new GridLayout(7,3);
        grid.setHgap(5);
        grid.setVgap(5);
        panel.setLayout(grid);
        panel.setBackground(new Color(165,165,165));
    }

    private void generateEntries(List<? extends Media> results) throws IOException {
        for (Media m : results) {
            if (m.getPosterPath() == null) {
                ImagePanel image = new ImagePanel("/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg");
                media.add(image.getLabel());
                labelMediaMap.put(image.getLabel(),m);
            } else {
                ImagePanel image = new ImagePanel(m.getPosterPath());
                media.add(image.getLabel());
                labelMediaMap.put(image.getLabel(),m);
            }
        }
        addListeners();
        loadEntries(parent);
    }

    private void addListeners() {
        for (JLabel label : media) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    showMediaDetails(label);
                }
            });
        }
    }

    private void showMediaDetails(JLabel label) {
        Media m = labelMediaMap.get(label);
        try {
            if (gui.getMode()) {
                new TVDetailsGUI(parent, m);
            } else {
                new MovieDetailsGUI(parent, m);
            }
            CardLayout cardLayout = (CardLayout) parent.getLayout();
            cardLayout.next(parent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // REQUIRES: parent != null
    // EFFECTS: adds all Image JLabels to this JPanel and this JPanel to the parent
    public void loadEntries(JComponent parent) {
        for (JLabel label : media) {
            panel.add(label);
        }
        parent.add(panel);
    }

    public List<JLabel> getMedia() {
        return media;
    }

}
