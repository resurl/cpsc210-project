import model.Database;
import model.Media;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.ApiManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database db;
    private Media movie;
    private Media show;

    @BeforeEach
    public void runBefore() {
        try {
            ApiManager manager = ApiManager.getInstance();
            db = new Database();
            //movie = manager.getMediaById(1895, "movie");
            movie = manager.dummyMovieCall();
            //show = manager.getMediaById(15260, "tv");
            show = manager.dummyShowCall();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddMovieToDatabase() {
        assertFalse(db.getMoviesMap().containsValue(movie));
        db.addMediaToDatabase(movie);
        assertEquals(1895, movie.getId());
        assertEquals(1895,db.getMediaId(movie));
        assertTrue(db.getMoviesMap().containsValue(movie));
    }

    @Test
    public void testAddShowToDatabase() {
        assertTrue(db.getShowsMap().isEmpty());
        assertFalse(db.getShowsMap().containsValue(show));
        db.addMediaToDatabase(show);
        assertEquals(15260, show.getId());
        assertEquals(15260, db.getMediaId(show));
        assertTrue(db.getShowsMap().containsValue(show));
    }
}
