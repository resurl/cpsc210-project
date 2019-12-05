package network;

import com.google.gson.*;
import model.Movie;
import model.Profile;

import java.lang.reflect.Type;

public class ProfileDeserializer implements JsonDeserializer<Profile> {
    private Gson gson = new Gson();
    private GsonBuilder gsonBuilder = new GsonBuilder();

    // REQUIRES: JSON is correctly formatted
    // EFFECTS: converts JSON to Profile object
    @Override
    public Profile deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        gsonBuilder.registerTypeAdapter(Movie.class, new MovieDeserializer());

        Profile profile = new Profile();

        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray movieArray = jsonObject.get("watched_movies").getAsJsonArray();
            for (JsonElement e : movieArray) {
                profile.addMovieToList(gson.fromJson(e,Movie.class));
            }
            JsonArray showArray = jsonObject.get("watched_shows").getAsJsonArray();
            for (JsonElement e : showArray) {
                JsonArray array = e.getAsJsonArray();
                profile.addExistingShow(array.get(0).getAsInt(),array.get(1).getAsInt());
            }
        } catch (JsonParseException e) {
            System.out.println("could not parse");
        }

        return profile;
    }
}
