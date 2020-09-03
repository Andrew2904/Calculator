package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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

    public static ImageIcon getIconFromResources(String filename) throws Exception {
        return new ImageIcon( getImageFromResources(filename, 100, 100) );
    }

    public static String getProperty(String property) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL res = null;
        FileInputStream file = null;
        Properties prop = null;
        String data = null;

        try {
            res = cl.getResource("format.props");

            file = new FileInputStream( res.getPath() );
            prop = new Properties();
            prop.load(file);

            data = prop.getProperty(property);
            file.close();
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return data;
    }

    public static String format(Date date){
        SimpleDateFormat format;

        try {
            format = new SimpleDateFormat( getProperty("date_read") );
        } catch (Exception e) {
            e.printStackTrace();
            format = new SimpleDateFormat("dd-MM-yyyy");
        }

        return format.format(date);
    }
}
