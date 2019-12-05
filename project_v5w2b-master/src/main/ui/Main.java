package ui;

import model.Database;

public class Main {

    private Database database;

    public static void main(String[] args) {
        Main main = new Main();
        new MainGUI(main.getDatabase());
        System.out.println("End of program.");
    }

    private Main() {
        database = new Database();
    }

    private Database getDatabase() {
        return database;
    }
}
