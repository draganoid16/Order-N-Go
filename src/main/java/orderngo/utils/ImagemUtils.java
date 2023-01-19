package orderngo.utils;

import java.awt.image.BufferedImage;
import java.sql.Blob;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImageFormats;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import java.sql.SQLException;

/**
 *
 * @author grupo1
 */
public final class ImagemUtils
{
    private ImagemUtils() {}
    
    //<editor-fold defaultstate="collapsed" desc="xxx -> Imagem">
    @SuppressWarnings("UseSpecificCatch")
    public static BufferedImage blobToImage(Blob imagemBlob) throws SQLException
    {
        BufferedImage imagem = null;
        try
        {
            imagem = Imaging.getBufferedImage(imagemBlob.getBinaryStream());
            imagemBlob.free();
        }
        catch (SQLException sqle)
        {
            throw sqle;
        } 
        catch (Exception ignored) {} 
        
        return imagem;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public static BufferedImage ficheiroToImage(String ficheiro)
    {
        BufferedImage imagem = null;
        try(InputStream fis = new FileInputStream(ficheiro))
        {
            imagem = Imaging.getBufferedImage(fis);
        }
        catch (Exception ignored) {}
        
        return imagem;
    }
    
    public static BufferedImage createImage(int width, int height)
    {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Imagem -> xxx">
    @SuppressWarnings("UseSpecificCatch")
    public static byte[] imageToByteArray(BufferedImage imagem)
    {
        byte[] data = null;
        try
        {
            data = Imaging.writeImageToBytes(imagem, ImageFormats.PNG);
        }
        catch (Exception ignored) {}
        
        return data;
    }
    
    @SuppressWarnings("UseSpecificCatch")
    public static InputStream imageToInputStream(BufferedImage imagem)
    {
        InputStream is = null;
        try
        {
            is = new ByteArrayInputStream(imageToByteArray(imagem));
        }
        catch (Exception ignored) {}
        
        return is;
    }
    //</editor-fold>
}
