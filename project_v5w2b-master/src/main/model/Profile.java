package model;

import model.tv.TvProgress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile {

    private List<Movie> watchedMovies;
    private Map<Integer, TvProgress> watchedTvShows;

    public Profile() {
        watchedMovies = new ArrayList<>();
        watchedTvShows = new HashMap<>();
    }

    // REQUIRES: !watchedTvShows.containsKey(id)
    // MODIFIES: TvProgress, this
    // EFFECTS: adds show id to list along with associated TvProgress
    public void addShowToList(int id) {
        if (!watchedTvShows.containsKey(id)) {
            TvProgress show = new TvProgress(id);
            watchedTvShows.put(id, show);
            show.assignToProfile(this);
        }
    }

    // REQUIRES: !watchedMovies.contains(movie)
    // EFFECTS: adds movie to watchedMovies
    public void addMovieToList(Movie movie) {
        if (!watchedMovies.contains(movie)) {
            watchedMovies.add(movie);
        }
    }

    // REQUIRES: show was previously watched by user
    // MODIFIES: TvProgress, this
    // EFFECTS: creates and adds TvProgress with specified numEpisodesWatched to watchedTvShows
    public void addExistingShow(int id, int numEps) {
        TvProgress tvProgress = new TvProgress(id);
        tvProgress.setNumOfEpisodesWatched(numEps);
        watchedTvShows.put(id, tvProgress);
        tvProgress.assignToProfile(this);
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public Map<Integer, TvProgress> getWatchedTvShows() {
        return watchedTvShows;
    }


}
