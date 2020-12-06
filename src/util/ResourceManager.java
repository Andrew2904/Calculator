package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ResourceManager {
    public static File getFileFromResources(String filename) throws Exception {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL res = cl.getResource(filename);

        if( res == null )
            throw new Exception("File \""+filename+"\" not found");
        else
            return new File(res.getFile());
    }

    public static Image getImageFromResources(String filename, int w, int h) throws Exception {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL res = cl.getResource(filename);

        if( res == null )
            throw new Exception("File \""+filename+"\" not found");
        else{
            BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();

            Image source = ImageIO.read(res);

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(source, 0, 0, w, h, null);
            g2d.dispose();
            //return new File(res.getFile());

            return resized;
        }
    }

    public static Image getImageFromResources(String filename) throws Exception {
        return getImageFromResources(filename, 10, 10);
    }
}
