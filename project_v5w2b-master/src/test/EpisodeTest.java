import model.tv.Episode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.ApiManager;


import static org.junit.jupiter.api.Assertions.*;

public class EpisodeTest {

    private Episode episode;
    private ApiManager manager;

    @BeforeEach
    public void runBefore() {
        try {
            manager = ApiManager.getInstance();
            //episode = manager.getEpisodeByID(13, 10, 15260);
            episode = manager.dummyEpisodeCall();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSettersAndGetters() {
        assertEquals(episode.getName(), "Come Along With Me");
        assertEquals(episode.getAirDate(), "2018-09-03");
        assertEquals(episode.getId(), 1563459);
        assertEquals(episode.getOverview(), "In the distant future, many years after the " +
                "outcome of the Gum War, two friends named Beth and Shermy recover a rusty old mechanical arm that " +
                "once belonged to Finn after it is dropped by a Prize Ball Guardian.");
    }

    @Test
    public void testEquals() {
        try {
            Episode episode1 = manager.getEpisodeByID(13,10,15260);
            Episode episode2 = manager.getEpisodeByID(13,10,15260);
            episode2.setSeasonNum(9);
            assertFalse(episode.equals(null));
            assertFalse(episode.equals(manager));
            assertFalse(episode.equals(manager.getEpisodeByID(12,10,15260)));
            assertFalse(episode.equals(episode2));
            episode2.setId(15259);
            assertFalse(episode.equals(episode2));
            assertTrue(episode.equals(episode1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotEquals() {
        try {
            Episode episode1 = manager.getEpisodeByID(1,10,15260);
            assertFalse(episode.equals(episode1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testHashcode() {
        try {
            Episode episode1 = manager.getEpisodeByID(13,10,15260);
            Episode episode2 = manager.getEpisodeByID(1,10,15260);
            assertEquals(episode.hashCode(), episode1.hashCode());
            assertNotEquals(episode.hashCode(), episode2.hashCode());
        } catch (Exception e) {
            fail();
        }
    }
}
