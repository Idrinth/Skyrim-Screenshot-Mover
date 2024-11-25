package de.idrinth.skyrimscreenshotmover;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class SteamFolderHandler extends BaseFolderHandler {

    public SteamFolderHandler(File screenshots, File gamefolder) {
        super(screenshots, gamefolder);
    }

    @Override
    protected void handle(File file) {
        try {
            if (isScreenshot(file)) {
                File target = new File(screenshots + "/" + getTimestamp(FileUtils.lastModified(file)) + ".jpg");
                FileUtils.copyFile(file, target);
                file.delete();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private boolean isScreenshot(File file) {
        if (file == null || !file.isFile()) {
            return false;
        }
        return file.getName().endsWith(".jpg") && file.getName().matches("^[0-9]+_[0-9]+\\.jpg$");
    }
}
