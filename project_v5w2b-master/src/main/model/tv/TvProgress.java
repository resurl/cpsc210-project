package model.tv;

import model.Profile;

import java.util.*;

public class TvProgress {

    private int id;
    private int numOfEpisodesWatched;
    private Profile profile;

    public TvProgress(int id) {
        this.id = id;
        numOfEpisodesWatched = 0;
    }


    // REQUIRES: profile is null
    // MODIFIES: Profile, this
    // EFFECTS: adds a profile to this and adds this to profile's watchedShows list
    public void assignToProfile(Profile profile) {
        if (this.profile == null) {
            this.profile = profile;
            //profile.addShowToList(this.id);
        }
    }

    public int getNumEps() {
        return numOfEpisodesWatched;
    }

    public Profile getProfile() {
        return profile;
    }

    // EFFECTS: increases numOfEpisodesWatched by 1
    public void incrementNumEps() {
        this.numOfEpisodesWatched++;

    }

    public void setNumOfEpisodesWatched(int num) {
        this.numOfEpisodesWatched = num;
    }

    // REQUIRES: numOfEpisodesWatched > 0
    // EFFECTS: decreases numOfEpisodesWatched by 1
    public void decrementNumEps() {
        if (numOfEpisodesWatched > 0) {
            this.numOfEpisodesWatched--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TvProgress that = (TvProgress) o;
        return id == that.id
                && numOfEpisodesWatched == that.numOfEpisodesWatched
                && profile.equals(that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numOfEpisodesWatched, profile);
    }
}
