package orderngo.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.sql.Blob;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

import java.sql.SQLException;
import java.io.IOException;

/**
 *
 * @author grupo1
 */
public class ImagemUtils
{
    private ImagemUtils() {}
    
    //<editor-fold defaultstate="collapsed" desc="xxx -> Imagem">
    public static BufferedImage blobToImage(Blob imagemBlob) throws SQLException
    {
        BufferedImage imagem = null;
        try
        {
            imagem = ImageIO.read(imagemBlob.getBinaryStream());
            imagemBlob.free();
        }
        catch (NullPointerException|IOException ignored) {}
        
        return imagem;
    }
    
    public static BufferedImage ficheiroToImage(String ficheiro)
    {
        BufferedImage imagem = null;
        try(InputStream fis = new FileInputStream(ficheiro))
        {
            imagem = ImageIO.read(fis);
        }
        catch (NullPointerException|IOException ignored) {}
        
        return imagem;
    }
    
    public static BufferedImage createImage(int width, int height)
    {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Imagem -> xxx">
    public static byte[] imageToByteArray(BufferedImage imagem)
    {
        byte[] data = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            ImageIO.write(imagem, "jpg", baos);
            data = baos.toByteArray();
        }
        catch (IllegalArgumentException|IOException ignored) {}
        
        return data;
    }
    
    public static InputStream imageToInputStream(BufferedImage imagem)
    {
        InputStream is = null;
        try
        {
            is = new ByteArrayInputStream(imageToByteArray(imagem));
        }
        catch (IllegalArgumentException|NullPointerException ignored) {}
        
        return is;
    }
    //</editor-fold>
}
