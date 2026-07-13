package de.idrinth.skyrimscreenshotmover;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;

abstract class BaseDeletingFolderHandler extends BaseFolderHandler {
    private final boolean mayConvert;
    public BaseDeletingFolderHandler(boolean mayConvert, File screenshots, File sourceFolder) {
        super(screenshots, sourceFolder);
        this.mayConvert = mayConvert;
    }

    public void handle(File file) {
        try {
            if (isScreenshot(file)) {
                if (mayConvert) {
                    File target = new File(screenshots + "/" + getTimestamp(FileUtils.lastModified(file)) + ".jpg");
                    ImageConverter.toJPEG(file, target);
                } else {
                    File target = new File(screenshots + "/" + getTimestamp(FileUtils.lastModified(file)) + "." + FilenameUtils.getExtension(file.getName()));
                    FileUtils.copyFile(file, target);
                }
                file.delete();
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
    abstract protected boolean isScreenshot(File file);
}
