import model.Database;
import model.tv.TvShow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.ApiManager;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TvShowTest {

    private TvShow show;
    private Database db;
    ApiManager manager = ApiManager.getInstance();

    @BeforeEach
    public void runBefore() {
        try {
            show = manager.dummyShowCall();
            db = new Database();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(show.getName(), "Adventure Time");
        assertEquals(show.getOverview(), "They’re not righteous, they’re wrongteous! Hook up with Finn and Jake as they travel the Land of Ooo searching for adventure. But remember, adventure isn’t always easy. Sometimes you’ve got to battle fire gnomes that torture old ladies, save a smelly hot dog princess from the Ice King, and thaw out a bunch of frozen businessmen. What the cabbage?!");
        assertEquals(15260,show.getId());
        assertNull(show.getTitle());
        assertEquals(293,show.getNumberOfEpisodes());
        assertEquals("/h5nQA8i7Z1M8yzDfDiJ9epmd1AD.jpg",show.getPosterPath());
    }

    @Test
    public void testGetNumberOfSeasons() {
        assertEquals(10, show.getNumberOfSeasons());
    }

    @Test
    public void testEquals()  {
        try {
            TvShow identical = show;
            TvShow almostIdentical = manager.dummyShowCall();
            almostIdentical.setId(10);
            assertTrue(show.equals(identical));
            assertFalse(show.equals(null));
            assertFalse(show.equals(db));
            assertFalse(show.equals(almostIdentical));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHashcode() {

        try {
            TvShow identical = show;
            TvShow almostIdentical = manager.dummyShowCall();
            almostIdentical.setId(10);
            assertEquals(show.hashCode(),identical.hashCode());
            assertNotEquals(show.hashCode(),almostIdentical.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
