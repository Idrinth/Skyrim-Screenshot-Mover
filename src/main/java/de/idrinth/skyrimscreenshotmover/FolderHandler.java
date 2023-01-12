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

public class FolderHandler implements Runnable {

    private final File screenshots;
    private final File gamefolder;
    private final Config config;

    public FolderHandler(File screenshots, File gamefolder, Config config) {
        this.screenshots = screenshots;
        this.gamefolder = gamefolder;
        this.config = config;
    }

    @Override
    public void run() {
        if (null == gamefolder || !gamefolder.isDirectory()) {
            return;
        }
        try {
            screenshots.mkdirs();
            ImageIO.setUseCache(false);
            for (File file : gamefolder.listFiles()) {
                handle(file);
            }
            WatchService watcher = FileSystems.getDefault().newWatchService();
            WatchKey key = FileSystems.getDefault().provider().getPath(gamefolder.toURI()).register(watcher, ENTRY_CREATE);
            (new Thread(() -> {
                while (true) {
                    for (WatchEvent event : key.pollEvents()) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                        handle(new File(gamefolder + "/" + ((Path) (event.context()))));
                    }
                }
            })).start();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void handle(File file) {
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
        if (file.getName().endsWith(".png") && file.getName().startsWith("ScreenShot")) {
            return true;
        }
        if (file.getName().equalsIgnoreCase("enbpalette.bmp") || file.getName().equalsIgnoreCase("enbsunsprite.bmp") || file.getName().equalsIgnoreCase("enbunderwaternoise.bmp")) {
            return false;
        }
        return file.getName().endsWith(".bmp") && file.getName().startsWith("enb");
    }

    private String getTimestamp(long time) {
        try {
            SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
            return dformat.format(new Timestamp(time));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return String.valueOf(time);
    }
}
