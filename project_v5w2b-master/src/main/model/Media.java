package model;

import java.util.Objects;

public abstract class Media {

    protected int id;
    protected String overview;
    protected String title;
    protected String name;
    protected String posterPath;

    public void setId(int id) {
        this.id = id;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media media = (Media) o;
        return id == media.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



