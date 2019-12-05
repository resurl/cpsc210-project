package network;

import com.google.gson.*;
import model.Movie;

import java.lang.reflect.Type;

public class MovieDeserializer implements JsonDeserializer<Movie> {

    public MovieDeserializer() {

    }

    // REQUIRES: JSON is correctly formatted
    // EFFECTS: serializes JSON to Movie object
    @Override
    public Movie deserialize(JsonElement jsonElement, Type type,
                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Movie movie = new Movie();
        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            movie.setId(jsonObject.get("id").getAsInt());
            movie.setTitle(jsonObject.get("title").getAsString());
            movie.setOverview(jsonObject.get("overview").getAsString());
            movie.setPosterPath(jsonObject.get("poster_path").getAsString());
        } catch (JsonParseException e) {
            System.out.println("Could not parse " + e.getMessage());
        }
        return movie;
    }
}
