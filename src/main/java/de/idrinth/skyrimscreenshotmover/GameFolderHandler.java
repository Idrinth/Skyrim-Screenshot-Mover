package de.idrinth.skyrimscreenshotmover;

import java.io.File;
import org.jetbrains.annotations.NotNull;

public class GameFolderHandler extends BaseDeletingFolderHandler {
    public GameFolderHandler(File screenshots, File gameFolder, @NotNull Config config) {
        super(config.mayConvert(), screenshots, gameFolder);
    }

    @Override
    protected boolean isScreenshot(File file) {
        if (file == null || !file.isFile()) {
            return false;
        }
        if (file.getName().endsWith(".png") && file.getName().startsWith("ScreenShot")) {
            return true;
        }
        if (file.getName().equalsIgnoreCase("enbpalette.bmp") || file.getName().equalsIgnoreCase("enbsunsprite.bmp") || file.getName().equalsIgnoreCase("enbunderwaternoise.bmp")) {
            return false;
        }
        return file.getName().endsWith(".bmp") && file.getName().startsWith("enb");
    }
}
