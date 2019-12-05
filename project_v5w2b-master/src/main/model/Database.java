package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import network.ProfileDeserializer;
import network.ProfileSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private Map<Integer, Media> tvShows;
    private Map<Integer, Media> movies;
    /*private Profile profile;
    private String fileName;
    private Gson gson = new Gson();
    private GsonBuilder gsonBuilder = new GsonBuilder();*/

    public Database() {
       /* gsonBuilder.registerTypeAdapter(Profile.class, new ProfileDeserializer());
        gsonBuilder.registerTypeAdapter(Profile.class, new ProfileSerializer());*/
        tvShows = new HashMap<>();
        movies = new HashMap<>();
        //profile = load();
        //fileName = "src/main/data/profile.txt";
    }

    // getter
    public Map<Integer, Media> getMoviesMap() {
        return movies;
    }

    public Map<Integer, Media> getShowsMap() {
        return tvShows;
    }

    /*public Profile getProfile() {
        return profile;
    }*/

    // REQUIRES: Media is of type Movie or TvShow
    // EFFECTS: adds media to tvShows map if TvShow.class
    //          or adds to movies map if Movie.class
    public void addMediaToDatabase(Media media) {
        if (media.getClass() == Movie.class) {
            this.addToMap(movies, media);
        } else {
            this.addToMap(tvShows,media);
        }
    }

    // REQUIRES: media is not already in map, mediaMap is initialized
    // EFFECTS: adds media to specified map
    private void addToMap(Map<Integer, Media> mediaMap, Media media) {
        if (!mediaMap.containsValue(media)) {
            mediaMap.put(media.getId(), media);
        }
    }

    public int getMediaId(Media media) {
        return media.getId();
    }

    // REQUIRES: file is in proper JSON formatting
    // EFFECTS: creates new Profile instance from JSON
    /*@Override
    public Profile load() {
        Profile newProfile = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            StringBuilder output = new StringBuilder();
            for (String s : lines) {
                output.append(s).append("\n");
            }
            newProfile = gson.fromJson(output.toString(), Profile.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newProfile;
    }*/

    // REQUIRES: file exists
    // EFFECTS: writes Profile in JSON format to file
    /*@Override
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter(new File(fileName),false);
        fileWriter.write(gson.toJson(this));
    }*/
}

