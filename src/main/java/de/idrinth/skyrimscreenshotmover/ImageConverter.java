package de.idrinth.skyrimscreenshotmover;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ImageConverter
{
    public static void toJPEG(File inputImg, File outputImg) throws IOException
    {
        try (FileInputStream input = FileUtils.openInputStream(inputImg); FileOutputStream output = FileUtils.openOutputStream(outputImg)) {
            ImageWriter writer = ImageIO.getImageWritersByFormatName("JPEG").next();
            ImageReader reader = ImageIO.getImageReadersByFormatName(FilenameUtils.getExtension(inputImg.getName())).next();
            writer.setOutput(ImageIO.createImageOutputStream(output));
            reader.setInput(ImageIO.createImageInputStream(input), true, true);
            writer.write(removeAlpha(reader.read(0, reader.getDefaultReadParam())));
            reader.dispose();
        }
    }
    private static BufferedImage removeAlpha(BufferedImage input)
    {
        if (!input.getColorModel().hasAlpha()) {
            return input;
        }
        BufferedImage target = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = target.createGraphics();
        g.fillRect(0, 0, input.getWidth(), input.getHeight());
        g.drawImage(input, 0, 0, null);
        g.dispose();
        return target;
    }
}