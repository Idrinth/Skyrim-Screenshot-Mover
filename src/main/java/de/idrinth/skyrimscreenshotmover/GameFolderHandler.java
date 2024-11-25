package de.idrinth.skyrimscreenshotmover;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class GameFolderHandler extends BaseFolderHandler {
    private final Config config;

    public GameFolderHandler(File screenshots, File gamefolder, Config config) {
        super(screenshots, gamefolder);
        this.config = config;
    }

    public void handle(File file) {
        try {
            if (isScreenshot(file)) {
                if (config.mayConvert()) {
                    File target = new File(screenshots + "/" + getTimestamp(FileUtils.lastModified(file)) + ".jpg");
                    ImageConverter.toJPEG(file, target);
                } else {
                    File target = new File(screenshots + "/" + getTimestamp(FileUtils.lastModified(file)) + "." + FilenameUtils.getExtension(file.getName()));
                    FileUtils.copyFile(file, target);
                }
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
        if (file.getName().endsWith(".png") && file.getName().startsWith("ScreenShot")) {
            return true;
        }
        if (file.getName().equalsIgnoreCase("enbpalette.bmp") || file.getName().equalsIgnoreCase("enbsunsprite.bmp") || file.getName().equalsIgnoreCase("enbunderwaternoise.bmp")) {
            return false;
        }
        return file.getName().endsWith(".bmp") && file.getName().startsWith("enb");
    }
}
