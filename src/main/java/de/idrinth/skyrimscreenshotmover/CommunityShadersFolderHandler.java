package de.idrinth.skyrimscreenshotmover;

import org.jetbrains.annotations.NotNull;
import java.io.File;

public class CommunityShadersFolderHandler extends BaseDeletingFolderHandler {
    public CommunityShadersFolderHandler(File screenshots, File shadersScreenshotDir, @NotNull Config config) {
        super(config.mayConvert(), screenshots, shadersScreenshotDir);
    }

    @Override
    protected boolean isScreenshot(File file) {
        if (file == null || !file.isFile()) {
            return false;
        }
        return file.getName().endsWith(".png") && file.getName().startsWith("CS_");
    }
}
