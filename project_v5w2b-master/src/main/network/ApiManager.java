package network;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import exceptions.InvalidValueException;
import model.*;
import model.tv.Episode;
import model.tv.TvSeason;
import model.tv.TvShow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ApiManager {

    private Gson gson;
    private GsonBuilder gsonBuilder;
    private String apiKey;
    private String theUrl;
    private URL url;
    private URLConnection request;

    private static final class ApiManagerHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    private ApiManager() {
        theUrl = "http://api.themoviedb.org/3/";
        apiKey = "74719049fabf39b6e169f25fc5f3a9fc";
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        gsonBuilder.registerTypeAdapter(TvShow.class, new TvShowDeserializer());
        gsonBuilder.registerTypeAdapter(Movie.class, new MovieDeserializer());
        gsonBuilder.registerTypeAdapter(Episode.class, new EpisodeDeserializer());
        gsonBuilder.registerTypeAdapter(TvSeason.class, new SeasonDeserializer());
        gson = gsonBuilder.create();
    }

    // EFFECTS: returns Singleton instance
    public static ApiManager getInstance() {
        return ApiManagerHolder.INSTANCE;
    }

    // REQUIRES: URL exists
    // EFFECTS: gets image from URL and returns it
    public Image loadImage(String imageExt) throws IOException {
        String imageUrl = "http://image.tmdb.org/t/p/w342" + imageExt;
        url = new URL(imageUrl);
        return ImageIO.read(url.openStream());
    }

    // REQUIRES: type is "movie" or "tv" && name is somewhat accurate
    // EFFECTS: gets JSON from API URL and returns list of Movie or TvShow objects
    public List<? extends Media> searchFor(String name, String type) throws IOException, InvalidValueException {
        String queryURL = generateSearchURL(type) + "&query=" + formatUrlString(name) + "&page=1&include_adult=false";

        JsonObject rootObj = getJsonFromURL(queryURL);
        JsonElement resultsObj = rootObj.get("results");
        Type listType = determineListType(type);

        return gson.fromJson(resultsObj,listType);
    }

    private Type determineListType(String type) throws InvalidValueException {
        if (type.equals("movie")) {
            return new TypeToken<List<Movie>>() {}.getType();
        } else if (type.equals("tv")) {
            return new TypeToken<List<TvShow>>() {}.getType();
        } else {
            throw new InvalidValueException("Invalid type passed in.");
        }
    }

    private Type determineMediaType(String type) throws InvalidValueException {
        if (type.equalsIgnoreCase("movie")) {
            return Movie.class;
        } else if (type.equalsIgnoreCase("tv")) {
            return TvShow.class;
        } else {
            throw new InvalidValueException("Wrong type passed in");
        }
    }

    private String generateSearchURL(String typeOfQuery) {
        return theUrl + "search/" + typeOfQuery + "?api_key=" + apiKey + "&language=en-US";
    }

    private String generateIdSearchURL(String typeOfQuery, int id) {
        return theUrl + typeOfQuery + "/" + id + "?api_key=" + apiKey + "&language=en-US";
    }

    //REQUIRES: seasonId and tvId are both accurate (correctly represents what we want), URL exists
    //EFFECTS: returns a TvSeason object
    public TvSeason getSeasonById(int seasonId, int tvId) throws IOException {
        String seasonQueryID = theUrl + "tv/" + tvId + "/season/" + seasonId + "?api_key=" + apiKey + "&language=en-US";
        TvSeason season = gson.fromJson(getJsonFromURL(seasonQueryID),TvSeason.class);
        season.setEpisodes(generateEpisodes(seasonId,tvId));
        return season;
    }

    // REQUIRES: seasonId and tvId correspond to desired episode list, URL exists
    // EFFECTS: returns list of Episodes for a specific season of a show
    public List<Episode> generateEpisodes(int seasonId, int tvId) throws IOException {
        List<Episode> episodeList = new ArrayList<>();
        String seasonQueryID = theUrl + "tv/" + tvId + "/season/" + seasonId + "?api_key=" + apiKey + "&language=en-US";
        JsonArray epListJson = getJsonFromURL(seasonQueryID).get("episodes").getAsJsonArray();
        for (JsonElement o : epListJson) {
            episodeList.add(gson.fromJson(o,Episode.class));
        }
        return episodeList;
    }

    // REQUIRES: URL exists, epId, seasonId, tvID are all accurate
    // EFFECTS: returns Episode object from API
    public Episode getEpisodeByID(int epId, int seasonId, int tvId) throws IOException {
        String episodeQueryURL = theUrl + "tv/" + tvId
                + "/season/" + seasonId + "/episode/" + epId + "?api_key=" + apiKey + "&language=en-US";

        return gson.fromJson(getJsonFromURL(episodeQueryURL),Episode.class);
    }

    // REQUIRES: type is "movie" or "tv", id exists in API
    // EFFECTS: returns Media object
    public Media getMediaById(int id, String type) throws InvalidValueException, IOException {
        String mediaQueryURL = generateIdSearchURL(type,id);
        return gson.fromJson(getJsonFromURL(mediaQueryURL), determineMediaType(type));
    }

    private JsonObject getJsonFromURL(String url) throws IOException {
        connectURL(url);
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        return root.getAsJsonObject();
    }

    // helper method
    private void connectURL(String query) throws IOException {
        url = new URL(query);
        request = url.openConnection();
        request.connect();
    }

    // EFFECTS: adds pluses instead of spaces for url formatting
    private String formatUrlString(String url) {
        return (url.replace(" ", "+"));
    }

    // REQUIRES: file exist
    // EFFECTS: returns TvShow from dummyshow_api.txt for testing
    public TvShow dummyShowCall() throws FileNotFoundException {
        String fileName = "src/main/data/dummyshow_api.txt";
        return gson.fromJson(new BufferedReader(new FileReader(fileName)),TvShow.class);
    }

    // REQUIRES: file exists
    // EFFECTS: returns Movie from dummymovie_api.txt for testing
    public Movie dummyMovieCall() throws FileNotFoundException {
        String filename = "src/main/data/dummymovie_api";
        return gson.fromJson(new BufferedReader(new FileReader(filename)),Movie.class);
    }

    // REQUIRES: file exists
    // EFFECTS: returns Episode from dummyepisode_api.txt for testing
    public Episode dummyEpisodeCall() throws FileNotFoundException {
        String filename = "src/main/data/dummyepisode_api";
        return gson.fromJson(new BufferedReader(new FileReader(filename)),Episode.class);
    }

    // REQUIRES: file exists
    // EFFECTS: returns TvSeason from dummyseason_api.txt for testing
    public TvSeason dummySeasonCall() throws FileNotFoundException {
        String filename = "src/main/data/dummyseason_api";
        return gson.fromJson(new BufferedReader(new FileReader(filename)),TvSeason.class);
    }

}
