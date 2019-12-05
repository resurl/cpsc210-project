package network;

import com.google.gson.*;
import model.tv.TvSeason;

import java.lang.reflect.Type;

public class SeasonDeserializer implements JsonDeserializer<TvSeason> {

    // REQURIRES: JSON is correctly formatted
    // EFFECTS: converts JSON to TvSeason object
    @Override
    public TvSeason deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        TvSeason season = new TvSeason();

        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            season.setAirDate(jsonObject.get("air_date").getAsString());
            season.setId(jsonObject.get("id").getAsInt());
            season.setName(jsonObject.get("name").getAsString());
            season.setOverview(jsonObject.get("overview").getAsString());
            season.setPosterPath(jsonObject.get("poster_path").getAsString());
            season.setSeasonNum(jsonObject.get("season_number").getAsInt());
        } catch (JsonParseException e) {
            System.out.println("Could not parse " + e.getMessage());
        }

        return season;
    }
}
