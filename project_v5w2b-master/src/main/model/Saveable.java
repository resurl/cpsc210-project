package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Saveable {
    Profile load() throws FileNotFoundException;

    void save() throws IOException;
}
