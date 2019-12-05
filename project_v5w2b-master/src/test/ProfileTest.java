import model.Movie;
import model.Profile;
import model.tv.TvProgress;
import model.tv.TvShow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.ApiManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile profile;
    private Movie movie;
    private TvShow show;
    private TvProgress showInProgress;

    @BeforeEach
    public void runBefore() throws IOException {
        try {
            ApiManager manager = ApiManager.getInstance();
            profile = new Profile();
            //movie = (Movie) manager.getMediaById(694,"movie");
            movie = manager.dummyMovieCall();
            //show = (TvShow) manager.getMediaById(60735, "tv");
            show = manager.dummyShowCall();
            showInProgress = new TvProgress(show.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testConstructor() {
        assertTrue(profile.getWatchedMovies().isEmpty());
        assertTrue(profile.getWatchedTvShows().isEmpty());
    }

    @Test
    public void testAddShowToList() {
        TvProgress tvProgress = new TvProgress(show.getId());
        tvProgress.assignToProfile(profile);
        assertFalse(profile.getWatchedTvShows().containsKey(show.getId()));
        assertFalse(profile.getWatchedTvShows().containsValue(tvProgress));
        profile.addShowToList(show.getId());
        assertTrue(profile.getWatchedTvShows().containsKey(show.getId()));
        assertTrue(profile.getWatchedTvShows().containsValue(tvProgress));

    }

    @Test
    public void testAddMovieToList() {
        assertFalse(profile.getWatchedMovies().contains(movie));
        profile.addMovieToList(movie);
        assertTrue(profile.getWatchedMovies().contains(movie));
    }

    @Test
    public void testAddExistingShowToList() {
        TvProgress tvProgress = new TvProgress(show.getId());
        tvProgress.setNumOfEpisodesWatched(show.getNumberOfEpisodes() - 5);
        tvProgress.assignToProfile(profile);
        assertFalse(profile.getWatchedTvShows().containsKey(show.getId()));
        assertFalse(profile.getWatchedTvShows().containsValue(tvProgress));
        profile.addExistingShow(show.getId(),tvProgress.getNumEps());
        assertTrue(profile.getWatchedTvShows().containsKey(show.getId()));
        assertTrue(profile.getWatchedTvShows().containsValue(tvProgress));

    }

}
