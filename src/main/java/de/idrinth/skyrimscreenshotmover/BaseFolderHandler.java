package de.idrinth.skyrimscreenshotmover;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public abstract class BaseFolderHandler implements Runnable {
    protected final File screenshots;
    protected final File sourceFolder;

    public BaseFolderHandler(File screenshots, File sourceFolder) {
        this.screenshots = screenshots;
        this.sourceFolder = sourceFolder;
    }

    @Override
    public void run() {
        if (null == sourceFolder || !sourceFolder.isDirectory()) {
            return;
        }
        try {
            screenshots.mkdirs();
            ImageIO.setUseCache(false);
            for (File file : Objects.requireNonNull(sourceFolder.listFiles())) {
                handle(file);
            }
            WatchService watcher = FileSystems.getDefault().newWatchService();
            WatchKey key = FileSystems.getDefault().provider().getPath(sourceFolder.toURI()).register(watcher, ENTRY_CREATE);
            (new Thread(() -> {
                while (true) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind().name().equals("ENTRY_CREATE")) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ignored) {
                            }
                            handle(new File(sourceFolder + "/" + ((Path) (event.context()))));
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            })).start();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    protected String getTimestamp(long time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
            return format.format(new Timestamp(time));
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return String.valueOf(time);
    }

    protected abstract void handle(File file);
}
