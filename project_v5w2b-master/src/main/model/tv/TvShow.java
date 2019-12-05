package model.tv;

import model.Database;
import model.Media;

import java.util.List;
import java.util.Map;

public class TvShow extends Media {

    private String firstAirDate;
    private String lastAirDate;
    private int numberOfEpisodes;
    private int numberOfSeasons;

    private transient List<Episode> episodes;
    private transient Map<Integer,List<Episode>> seasons;

    // setters and getters
    public int getNumberOfSeasons() {
        return this.numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public void setNumOfEpisodes(int numOfEpisodes) {
        this.numberOfEpisodes = numOfEpisodes;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

}
