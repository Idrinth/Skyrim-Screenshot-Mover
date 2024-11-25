package de.idrinth.skyrimscreenshotmover;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public abstract class BaseFolderHandler implements Runnable {
    protected final File screenshots;
    protected final File gamefolder;

    public BaseFolderHandler(File screenshots, File gamefolder) {
        this.screenshots = screenshots;
        this.gamefolder = gamefolder;
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
                        if (event.kind().name().equals("ENTRY_CREATE")) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                            }
                            handle(new File(gamefolder + "/" + ((Path) (event.context()))));
                        }
                    }
                }
            })).start();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    protected String getTimestamp(long time) {
        try {
            SimpleDateFormat dformat = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
            return dformat.format(new Timestamp(time));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return String.valueOf(time);
    }

    protected abstract void handle(File file);
}
