package network;

import com.google.gson.*;
import model.Movie;
import model.Profile;

import java.lang.reflect.Type;

public class ProfileSerializer implements JsonSerializer<Profile> {

    private Gson gson = new Gson();
    private GsonBuilder gsonBuilder = new GsonBuilder();

    // REQUIRES: Profile fields != null
    // EFFECTS: converts Profile object to JSON
    @Override
    public JsonElement serialize(Profile profile, Type type, JsonSerializationContext jsonSerializationContext) {
        gsonBuilder.registerTypeAdapter(Movie.class, new MovieSerializer());
        JsonObject jsonObject = new JsonObject();
        JsonArray movieArray = new JsonArray();

        for (Movie m : profile.getWatchedMovies()) {
            String string = gson.toJson(m);
            movieArray.add(string);
        }

        jsonObject.add("watched_movies",movieArray);

        JsonArray tvArray = new JsonArray();

        for (Integer i : profile.getWatchedTvShows().keySet()) {
            JsonArray keyValue = new JsonArray();
            keyValue.add(i.toString());
            keyValue.add(profile.getWatchedTvShows().get(i).getNumEps());
        }
        jsonObject.add("watched_shows",tvArray);

        return jsonObject;
    }
}
