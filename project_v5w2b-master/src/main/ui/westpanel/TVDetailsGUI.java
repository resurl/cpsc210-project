package ui.westpanel;

import model.Media;
import model.tv.TvShow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TVDetailsGUI extends MediaDetailsGUI {

    public TVDetailsGUI(JComponent component, Media m) throws IOException {
        super(component,m);
        component.add(panel);
    }

    //REQUIRES: URL for image exists
    //EFFECTS: creates JPanel for a TvShow instance
    @Override
    protected void initGraphics() throws IOException {
        panel.setLayout(new GridBagLayout());
        JLabel image = imagePanel();
        panel.add(image, constraints);
        addTitle(media.getName());
        addOverview();
        addMiscellaneousDetails();
    }

    private void addMiscellaneousDetails() {
        TvShow show = (TvShow) media;
        JLabel seasonNums = new JLabel("# of Seasons: " + show.getNumberOfSeasons());
        setConstraint(1,2,1,1);
        panel.add(seasonNums,constraints);
        JLabel episodeNums = new JLabel("# of Episodes: " + show.getNumberOfEpisodes());
        setConstraint(2,2,1,1);
        panel.add(episodeNums,constraints);
    }
}

