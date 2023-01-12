package de.idrinth.skyrimscreenshotmover;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

public class ConfigFactory {
    public Config build() throws IOException
    {
        File file = new File("screenshotmover.yaml");
        if (!file.isFile()) {
            JOptionPane.showMessageDialog(
                null,
                "The configuration has new values and was written to screenshotmover.yaml.",
                "New Configuration.",
                JOptionPane.WARNING_MESSAGE
            );
            FileUtils.write(file, (new Config()).toString(), "utf-8");
            return null;
        }
        Config config = (new ObjectMapper(new YAMLFactory())).readValue(file, Config.class);
        if (!FileUtils.readFileToString(file, "utf-8").trim().equals(config.toString().trim())) {
            JOptionPane.showMessageDialog(
                null,
                "The configuration has new values and was written to screenshotmover.yaml.",
                "New Configuration.",
                JOptionPane.WARNING_MESSAGE
            );
            FileUtils.write(file, config.toString(), "utf-8");
            return null;
        }
        return config;
    }
}
