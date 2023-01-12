package de.idrinth.skyrimscreenshotmover;

import java.io.File;
import java.io.IOException;

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
        if (start(
            config.getSpecial(),
            "HKLM\\SOFTWARE\\WOW6432Node\\Bethesda Softworks\\Skyrim Special Edition",
            config
        )) {
            ui.activateSpecial();
        }
        if (start(
            config.getLegacy(),
            "HKLM\\SOFTWARE\\WOW6432Node\\Bethesda Softworks\\skyrim",
            config
        )) {
            ui.activateLegacy();
        }
        if (start(
            config.getLegacy(),
            "HKLM\\SOFTWARE\\WOW6432Node\\Bethesda Softworks\\Skyrim VR",
            config
        )) {
            ui.activateVirtualReality();
        }
    }
    private static boolean start(String picDir, String regDir, Config config)
    {
        String reg = WindowsRegistry.readRegistry(
            regDir,
            "installed path"
        );
        if (null == reg) {
            return false;
        }
        new Thread(new FolderHandler(
            new File(picDir),
            new File(reg.trim()),
            config
        )).start();
        return true;
    }
}
