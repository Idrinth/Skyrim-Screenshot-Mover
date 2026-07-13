package de.idrinth.skyrimscreenshotmover;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UI implements Runnable {
    private final GameLabel legacy = new GameLabel("Legacy", 5, 15);
    private final GameLabel special = new GameLabel("Special", 5, 30);
    private final GameLabel vr = new GameLabel("Virtual Reality", 5, 45);
    private final StatusLabel legacyLabelGame = new StatusLabel(65, 15, "Game");
    private final StatusLabel specialLabelGame = new StatusLabel(65, 30, "Game");
    private final StatusLabel vrLabelGame = new StatusLabel(65, 45, "Game");
    private final StatusLabel legacyLabelSteam = new StatusLabel(145, 15, "Steam");
    private final StatusLabel specialLabelSteam = new StatusLabel(145, 30, "Steam");
    private final StatusLabel vrLabelSteam = new StatusLabel(145, 45, "Steam");
    private final StatusLabel legacyLabelCommunityShaders = new StatusLabel(225, 15, "CommunityShaders");
    private final StatusLabel specialLabelCommunityShaders = new StatusLabel(225, 30, "CommunityShaders");
    private final StatusLabel vrLabelCommunityShaders = new StatusLabel(225, 45, "CommunityShaders");
    public void run()
    {
        JFrame frame = new JFrame("Skyrim Screenshot Mover");  
        JButton button = new JButton("Exit");
        button.setBounds(0,100,375,30);
        frame.add(legacy);
        frame.add(special);
        frame.add(vr);
        frame.add(legacyLabelGame);
        frame.add(specialLabelGame);
        frame.add(vrLabelGame);
        frame.add(legacyLabelSteam);
        frame.add(specialLabelSteam);
        frame.add(vrLabelSteam);
        frame.add(legacyLabelCommunityShaders);
        frame.add(specialLabelCommunityShaders);
        frame.add(vrLabelCommunityShaders);
        frame.add(button);
        frame.setSize(375,170);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button.addActionListener((ActionEvent e) -> {
            System.exit(0);  
        });
    }
    public void activateLegacyGame()
    {
        legacyLabelGame.setStatusActive();
    }
    public void activateSpecialGame()
    {
        specialLabelGame.setStatusActive();
    }
    public void activateVirtualRealityGame()
    {
        vrLabelGame.setStatusActive();
    }
    public void activateLegacySteam()
    {
        legacyLabelSteam.setStatusActive();
    }
    public void activateSpecialSteam()
    {
        specialLabelSteam.setStatusActive();
    }
    public void activateVirtualRealitySteam()
    {
        vrLabelSteam.setStatusActive();
    }
    public void activateLegacyCommunityShaders()
    {
        legacyLabelCommunityShaders.setStatusActive();
    }
    public void activateVRCommunityShaders()
    {
        vrLabelCommunityShaders.setStatusActive();
    }
    public void activateSpecialCommunityShaders()
    {
        specialLabelCommunityShaders.setStatusActive();
    }
}
