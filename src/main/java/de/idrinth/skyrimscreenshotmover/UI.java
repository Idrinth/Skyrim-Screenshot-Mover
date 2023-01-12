package de.idrinth.skyrimscreenshotmover;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class UI implements Runnable {
    private final JLabel legacy = new JLabel("[ ] Legacy Edition");
    private final JLabel special = new JLabel("[ ] Special Edition");
    private final JLabel vr = new JLabel("[ ] VR Edition");
    public void run()
    {
        JFrame frame = new JFrame("Skyrim Screenshot Mover");  
        JButton button = new JButton("Exit");
        legacy.setBounds(15,0,115,100);
        special.setBounds(115,0,220,100);
        vr.setBounds(220,0,325,100);
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
    }
    public void activateLegacy()
    {
        legacy.setText("[X] Legacy Edition");
    }
    public void activateSpecial()
    {
        special.setText("[X] Special Edition");
    }
    public void activateVirtualReality()
    {
        vr.setText("[X] VR Edition");
    }
}
