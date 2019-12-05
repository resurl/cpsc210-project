package network;

import com.google.gson.*;
import model.tv.TvShow;

import java.lang.reflect.Type;

public class TvShowDeserializer implements JsonDeserializer<TvShow> {

    public TvShowDeserializer() {
    }

    //REQUIRES: JSON Is correctly formatted
    //EFFECTS: converts JSON from API to TvShow object
    @Override
    public TvShow deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        TvShow show = new TvShow();
        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            deserializeConstantFields(show,jsonObject);
            if (jsonObject.get("number_of_episodes") != null) {
                show.setNumOfEpisodes(jsonObject.get("number_of_episodes").getAsInt());
            }
            if (jsonObject.get("number_of_seasons") != null) {
                show.setNumberOfSeasons(jsonObject.get("number_of_seasons").getAsInt());
            }
            if (!jsonObject.get("poster_path").isJsonNull()) {
                show.setPosterPath(jsonObject.get("poster_path").getAsString());
            }
        } catch (JsonParseException e) {
            System.out.println("Could not parse " + e.getMessage());
        }
        return show;
    }

    private void deserializeConstantFields(TvShow show, JsonObject jsonObject) {
        show.setId(jsonObject.get("id").getAsInt());
        show.setName(jsonObject.get("name").getAsString());
        show.setOverview(jsonObject.get("overview").getAsString());
        show.setFirstAirDate(jsonObject.get("first_air_date").getAsString());

    }
}
