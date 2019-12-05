package network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Movie;

import java.lang.reflect.Type;

public class MovieSerializer implements JsonSerializer<Movie> {

    // EFFECTS: serializes Movie object to JSON
    @Override
    public JsonElement serialize(Movie movie, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", movie.getId());
        jsonObject.addProperty("poster_path",movie.getPosterPath());
        jsonObject.addProperty("title",movie.getTitle());
        jsonObject.addProperty("overview",movie.getOverview());

        return jsonObject;
    }
}
