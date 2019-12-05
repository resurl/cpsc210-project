import exceptions.InvalidValueException;
import model.Database;
import model.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import network.ApiManager;

import java.io.IOException;

public class MovieTest {

    private Media movie;
    private ApiManager manager;
    private Database db;

    @BeforeEach
    public void runBefore() throws IOException, InvalidValueException {
        db = new Database();
        manager = ApiManager.getInstance();
        //movie = manager.getMediaById(1895,"movie");
        movie = manager.dummyMovieCall();
    }

    @Test
    public void testSettersAndGetters() {
        assertEquals("Star Wars: Episode III - Revenge of the Sith", movie.getTitle());
        assertEquals(1895, movie.getId());
        assertNull(movie.getName());
        assertEquals("The evil Darth Sidious enacts his final plan for unlimited power -- and the heroic Jedi Anakin Skywalker must choose a side.",movie.getOverview());
    }

    @Test
    public void testEquals() {

        try {
            Media movie2 = manager.getMediaById(1895,"movie");
            assertEquals(movie, movie2);
            assertTrue(movie.equals(movie2));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testNotEquals() {
        try{
            Media movie2 = manager.getMediaById(152601,"movie");
            assertFalse(movie.equals(movie2));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testHashcode() {
        try {
            Media movie2 = manager.getMediaById(1895,"movie");
            assertEquals(movie.hashCode(),movie2.hashCode());
        } catch (Exception e) {
            fail();
        }
    }
}
