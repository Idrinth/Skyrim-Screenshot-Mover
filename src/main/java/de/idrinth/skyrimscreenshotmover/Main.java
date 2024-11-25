package de.idrinth.skyrimscreenshotmover;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Config config = new ConfigFactory().build();
        if (config == null) {
            return;
        }
        UI ui = new UI();
        new Thread(ui).start();
        // Game folders
        if (startGame(
                config.getSpecial(),
                config.specialGame,
                config
        )) {
            ui.activateSpecialGame();
        }
        if (startGame(
                config.getLegacy(),
                config.legacyGame,
                config
        )) {
            ui.activateLegacyGame();
        }
        if (startGame(
                config.getVr(),
                config.vrGame,
                config
        )) {
            ui.activateVirtualRealityGame();
        }
        // Steam 32 bit
        if (startSteam(
            config.getLegacy(),
            config.steam32,
            config,
            config.legacyId
        )) {
            ui.activateLegacySteam();
        }
        if (startSteam(
            config.getSpecial(),
            config.steam32,
            config,
            config.specialId
        )) {
            ui.activateSpecialSteam();
        }
        if (startSteam(
            config.getVr(),
            config.steam32,
            config,
            config.vrId
        )) {
            ui.activateVirtualRealitySteam();
        }
        // Steam 64 bit
        if (startSteam(
            config.getLegacy(),
            config.steam64,
            config,
            config.legacyId
        )) {
            ui.activateLegacySteam();
        }
        if (startSteam(
            config.getSpecial(),
            config.steam64,
            config,
            config.specialId
        )) {
            ui.activateSpecialSteam();
        }
        if (startSteam(
            config.getVr(),
            config.steam64,
            config,
            config.vrId
        )) {
            ui.activateVirtualRealitySteam();
        }
    }
    private static boolean startSteam(String picDir, String regDir, Config config, long GameId)
    {
        String reg = WindowsRegistry.readRegistry(
            regDir,
            "InstallPath"
        );
        if (null == reg) {
            return false;
        }
        File userdir  = new File(reg.trim() + "\\userdata");
        if (! userdir.isDirectory()) {
            return false;
        }
        int i = 0;
        for (File f : Objects.requireNonNull(userdir.listFiles())) {
            if (f.isDirectory() && f.getName().matches("^[0-9]+$")) {
                File screenshotFolder = new File(f + "\\760\\remote\\" + GameId + "\\screenshots");
                if (screenshotFolder.isDirectory()) {
                    new Thread(new SteamFolderHandler(
                        new File(picDir),
                        screenshotFolder
                    )).start();
                    i ++;
                }
            }
        }
        return i > 0;
    }
    private static boolean startGame(String picDir, String regDir, Config config)
    {
        String reg = WindowsRegistry.readRegistry(
            regDir,
            "installed path"
        );
        if (null == reg) {
            return false;
        }
        new Thread(new GameFolderHandler(
            new File(picDir),
            new File(reg.trim()),
            config
        )).start();
        return true;
    }
}
