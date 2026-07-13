package de.idrinth.skyrimscreenshotmover;

import javax.swing.*;

public class GameLabel extends JLabel {
    GameLabel(String text, int x, int y) {
        super();
        setText(text);
        setBounds(x, y, 60, 30);
    }
}
