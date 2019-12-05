package network;

import com.google.gson.*;
import model.tv.Episode;

import java.lang.reflect.Type;

public class EpisodeDeserializer implements JsonDeserializer<Episode>  {

    public EpisodeDeserializer() {

    }

    // REQUIRES: JSON is correctly formatted
    // EFFECTS: serializes JSON to Episode object
    @Override
    public Episode deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Episode episode = new Episode();
        try {
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            episode.setId(jsonObj.get("id").getAsInt());
            episode.setName(jsonObj.get("name").getAsString());
            episode.setOverview(jsonObj.get("overview").getAsString());
            episode.setAirDate(jsonObj.get("air_date").getAsString());
            episode.setEpisodeNum(jsonObj.get("episode_number").getAsInt());
            episode.setSeasonNum(jsonObj.get("season_number").getAsInt());
        } catch (JsonParseException e) {
            System.out.println("Could not parse " + e.getMessage());
        }
        return episode;
    }
}
