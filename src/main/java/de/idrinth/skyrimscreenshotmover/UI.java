package de.idrinth.skyrimscreenshotmover;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class UI implements Runnable {
    private final JLabel legacy = new JLabel();
    private final JLabel special = new JLabel();
    private final JLabel vr = new JLabel();
    private boolean specialGame = false;
    private boolean legacyGame = false;
    private boolean vrGame = false;
    private boolean specialSteam = false;
    private boolean legacySteam = false;
    private boolean vrSteam = false;
    public void run()
    {
        JFrame frame = new JFrame("Skyrim Screenshot Mover");  
        JButton button = new JButton("Exit");
        legacy.setBounds(5,0,115,100);
        special.setBounds(115,0,235,100);
        vr.setBounds(235,0,350,100);
        button.setBounds(0,100,350,100);
        frame.add(legacy);
        frame.add(special);
        frame.add(vr);
        frame.add(button);
        frame.setSize(350,250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button.addActionListener((ActionEvent e) -> {
            System.exit(0);  
        });
        updateLabels();
    }
    private void updateLabels()
    {
        if (legacyGame && legacySteam) {
            legacy.setText("[G][S] Legacy Edition");
        } else if (legacyGame) {
            legacy.setText("[G][ ] Legacy Edition");
        } else if (legacySteam) {
            legacy.setText("[ ][S] Legacy Edition");
        } else {
            legacy.setText("[ ][ ] Legacy Edition");
        }
        if (specialGame && specialSteam) {
            special.setText("[G][S] Special Edition");
        } else if (specialGame) {
            special.setText("[G][ ] Special Edition");
        } else if (specialSteam) {
            special.setText("[ ][S] Special Edition");
        } else {
            special.setText("[ ][ ] Special Edition");
        }
        if (vrGame && vrSteam) {
            vr.setText("[G][S] VR Edition");
        } else if (vrGame) {
            vr.setText("[G][ ] VR Edition");
        } else if (vrSteam) {
            vr.setText("[ ][S] VR Edition");
        } else {
            vr.setText("[ ][ ] VR Edition");
        }
    }
    public void activateLegacyGame()
    {
        legacyGame = true;
        updateLabels();
    }
    public void activateSpecialGame()
    {
        specialGame = true;
        updateLabels();
    }
    public void activateVirtualRealityGame()
    {
        vrGame = true;
        updateLabels();
    }
    public void activateLegacySteam()
    {
        legacySteam = true;
        updateLabels();
    }
    public void activateSpecialSteam()
    {
        specialSteam = true;
        updateLabels();
    }
    public void activateVirtualRealitySteam()
    {
        vrSteam = true;
        updateLabels();
    }
}
