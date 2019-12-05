package ui;

import exceptions.InvalidValueException;
import model.*;
import network.ApiManager;
import ui.westpanel.DisplayGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainGUI {

    private static final int HEIGHT = 1080;
    private static final int WIDTH = 1920;

    private Database database;
    private Boolean mode; // 0 is movie, 1 is tv
    private ApiManager apiManager;
    private javax.swing.JFrame guiFrame;
    private JPanel cardPanel;
    private JScrollPane westPanel;
    private CardLayout cardLayout;
    private JTextArea errorOutput;
    private JTextField userInput;
    private List<JButton> buttons;

    public MainGUI(Database database) {
        apiManager = ApiManager.getInstance();
        this.database = database;
        guiFrame = new javax.swing.JFrame();
        buttons = new ArrayList<>();
        initializeGraphics();
    }

    public boolean getMode() {
        return mode;
    }

    private void initializeGraphics() {
        guiFrame.setLayout(new BorderLayout());
        guiFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        guiFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        guiFrame.setResizable(false);
        createPanels();
        loadUserInputPanel();
        guiFrame.add(westPanel,BorderLayout.WEST);
        new DisplayGUI(cardPanel,this);
        guiFrame.setVisible(true);
    }

    private void createPanels() {
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        westPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        customizeScrollPane();
        westPanel.setViewportView(cardPanel);
    }

    private void customizeScrollPane() {
        westPanel.setPreferredSize(new Dimension((WIDTH / 3) * 2,HEIGHT));
        JScrollBar scroll = westPanel.getVerticalScrollBar();
        scroll.setUnitIncrement(5);
    }

    private void createButtons(JComponent parent) {
        JButton movieButton = new JButton("Search for movies");
        JButton tvButton = new JButton("Search for TV shows");
        JButton profileButton = new JButton("Profile");
        buttons.add(movieButton);
        buttons.add(tvButton);
        buttons.add(profileButton);
        movieButton.addActionListener(e -> mediaSearch("movie"));
        tvButton.addActionListener(e -> mediaSearch("tv"));
        //profileButton.addActionListener(e -> findProfile());
        for (JButton b : buttons) {
            b.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
            parent.add(b);
        }
    }

    private void mediaSearch(String type) {
        if (!userInput.getText().equals("")) {
            try {
                setSearchMode(type);
                List<? extends Media> results = apiManager.searchFor(userInput.getText(), type);
                new DisplayGUI(cardPanel, results,this);
                cardLayout.next(cardPanel);
            } catch (IOException | InvalidValueException e) {
                errorOutput.setText("Error! \n" + e.getMessage());
            } catch (Exception e) {
                errorOutput.setText("Unable to search! \n" + e.getMessage());
            }
        } else {
            errorOutput.setText("Nothing was entered in the search bar!");
        }
    }

    private void setSearchMode(String type) {
        switch (type) {
            case "tv":
                mode = true;
                break;
            default:
                mode = false;
        }
    }

    private void findProfile() {

    }

    private void createUserInputField() {
        userInput = new JTextField();
        userInput.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,32));
        userInput.setBorder(BorderFactory.createCompoundBorder(userInput.getBorder(),
                BorderFactory.createEmptyBorder(50,50,50,50)));
    }

    private void createFiller(JComponent parent) {
        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(WIDTH / 3, 75));
        parent.add(empty);
    }

    private void loadUserInputPanel() {
        JPanel userInputPanel = new JPanel();
        GridLayout grid = new GridLayout(7,1);
        grid.setVgap(15);
        userInputPanel.setLayout(grid);
        userInputPanel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        createFiller(userInputPanel);
        createUserInputField();
        userInputPanel.add(userInput);
        createFiller(userInputPanel);
        createButtons(userInputPanel);
        errorOutput = new JTextArea();
        customizeOutputArea(errorOutput);
        userInputPanel.add(errorOutput);
        guiFrame.add(userInputPanel, BorderLayout.EAST);
    }

    private void customizeOutputArea(JTextArea area) {
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFocusable(false);
        area.setBackground(UIManager.getColor("Label.background"));
        area.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    }
}
