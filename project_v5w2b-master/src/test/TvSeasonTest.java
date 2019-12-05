import model.Media;
import model.tv.Episode;
import model.tv.TvSeason;

import model.tv.TvShow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TvSeasonTest {

    private TvSeason season;
    private Episode episode;
    private Media show;

    @BeforeEach
    public void runBefore() {
        try {
            ApiManager manager = ApiManager.getInstance() ;
            //show = manager.getMediaById(15260, "tv");
            show = manager.dummyShowCall();
            //season = manager.getSeasonById(10,15260);
            season = manager.dummySeasonCall();
            episode = manager.dummyEpisodeCall();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("2017-09-17",season.getAirDate());
        assertEquals("Season 10", season.getName());
        assertEquals("The tenth and final season of Adventure Time.", season.getOverview());
        assertEquals("/zNxDweupTQ8S7U7sfzNuDDeapYZ.jpg",season.getPosterPath());
        assertEquals(97766,season.getId());
        assertEquals(10,season.getSeasonNum());
    }

    @Test
    public void testSetShow() {
        season.setTvShow((TvShow) show);
        assertEquals(show, season.getTvShow());
    }

    @Test
    public void testSetEpisodes() {
        List<Episode> episodeList = new ArrayList<>();
        episodeList.add(episode);
        assertNull(season.getEpisodes());
        season.setEpisodes(episodeList);
        assertTrue(season.getEpisodes().contains(episode));
        assertEquals(season.getEpisodes(),episodeList);
    }

}
