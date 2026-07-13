package de.idrinth.skyrimscreenshotmover;

import javax.swing.*;

public class StatusLabel extends JLabel {
    String nameIfActive;
    StatusLabel(int x, int y, String nameIfActive) {
        super();
        setBounds(x, y, 80, 30);
        setText("[ / ]");
        this.nameIfActive = nameIfActive;
    }
    void setStatusActive()
    {
        setText("[ " + nameIfActive + " ]");
    }
}
