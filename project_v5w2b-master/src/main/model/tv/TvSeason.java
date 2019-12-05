package model.tv;

import java.util.List;

public class TvSeason {

    private String airDate;
    private List<Episode> episodes;
    private String name;
    private String overview;
    private int id;
    private String posterPath;
    private int seasonNum;

    private TvShow tvShow;

    public TvSeason() {

    }

    // setters and getters
    public void setAirDate(String airDate) {
        this.airDate = airDate;
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

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }

    public void setTvShow(TvShow show) {
        this.tvShow = show;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
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

    public String getPosterPath() {
        return posterPath;
    }

    public int getSeasonNum() {
        return seasonNum;
    }

    public TvShow getTvShow() {
        return this.tvShow;
    }
}
