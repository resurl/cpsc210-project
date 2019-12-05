import model.Profile;
import model.tv.Episode;
import model.tv.TvProgress;
import model.tv.TvShow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TvProgressTest {

    private TvProgress showInProgress;
    private TvShow show;

    @BeforeEach
    public void runBefore() {
        try {
            ApiManager manager = ApiManager.getInstance();
            //show = (TvShow) manager.getMediaById(60735, "tv");
            show = manager.dummyShowCall();
            //episode = manager.getEpisodeByID(1, 6, 60735);
            //episode = manager.dummyEpisodeCall();
            showInProgress = new TvProgress(show.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void assignToProfile() {
        Profile profile = new Profile();
        assertNull(showInProgress.getProfile());
        showInProgress.assignToProfile(profile);
        assertEquals(profile, showInProgress.getProfile());
    }

    @Test
    public void testGetEpsWatchedInSeason() {
        assertEquals(0,showInProgress.getNumEps());
    }

    @Test
    public void testIncrementAndDecrementNumEps() {
        showInProgress.setNumOfEpisodesWatched(3);
        assertEquals(3,showInProgress.getNumEps());
        showInProgress.incrementNumEps();
        assertEquals(4,showInProgress.getNumEps());
        showInProgress.decrementNumEps();
        assertEquals(3,showInProgress.getNumEps());

    }

    @Test
    public void testEquals() {
        Profile profile = new Profile();
        Profile otherProfile = new Profile();
        TvProgress tvProgress = new TvProgress(1);
        TvProgress identical = new TvProgress(show.getId());
        TvProgress almostIdentical = new TvProgress(show.getId());
        almostIdentical.assignToProfile(otherProfile);
        identical.assignToProfile(profile);
        assertFalse(showInProgress.equals(null));
        showInProgress.assignToProfile(profile);
        assertFalse(showInProgress.equals(tvProgress));
        tvProgress.assignToProfile(profile);
        assertFalse(showInProgress.equals(tvProgress));
        assertTrue(showInProgress.equals(identical));
        assertFalse(showInProgress.equals(almostIdentical));
        assertTrue(showInProgress.equals(showInProgress));
    }

    @Test
    public void testHashcode() {
        Profile profile = new Profile();
        Profile otherProfile = new Profile();
        TvProgress tvProgress = new TvProgress(1);
        TvProgress identical = new TvProgress(show.getId());
        TvProgress almostIdentical = new TvProgress(show.getId());
        showInProgress.setNumOfEpisodesWatched(2);
        identical.setNumOfEpisodesWatched(2);
        showInProgress.assignToProfile(profile);
        identical.assignToProfile(profile);
        almostIdentical.assignToProfile(otherProfile);
        almostIdentical.setNumOfEpisodesWatched(2);
        tvProgress.setNumOfEpisodesWatched(1);
        assertNotEquals(tvProgress.hashCode(),showInProgress.hashCode());
        assertEquals(showInProgress.hashCode(),identical.hashCode());
        assertNotEquals(almostIdentical.hashCode(),showInProgress.hashCode());
    }

}
