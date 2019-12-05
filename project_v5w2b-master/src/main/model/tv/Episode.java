package model.tv;

import java.util.Objects;

public class Episode {

    private String airDate;
    private int episodeNum;
    private String name;
    private String overview;
    private int id;
    private int seasonNum;

    private TvSeason season;
    private TvShow show;

    public Episode() {

    }

    //setters and getters
    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Episode episode = (Episode) o;
        return episodeNum == episode.episodeNum
                && id == episode.id
                && seasonNum == episode.seasonNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeNum, id, seasonNum);
    }
}
