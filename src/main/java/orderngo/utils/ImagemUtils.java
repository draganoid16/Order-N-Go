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
    public static BufferedImage blobToImage(Blob imagemBlob) throws SQLException
    {
        BufferedImage imagem = null;
        try
        {
            imagem = ImageIO.read(imagemBlob.getBinaryStream());
            imagemBlob.free();
        }
        catch (NullPointerException|IOException ex) {}
        
        return imagem;
    }
    
    public static InputStream imageToInputStream(BufferedImage imagem)
    {
        InputStream is = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            ImageIO.write(imagem, "png", baos);
            is = new ByteArrayInputStream(baos.toByteArray());
        }
        catch (IllegalArgumentException|IOException ex) {}
        
        return is;
    }
    
    public static BufferedImage ficheiroToImage(String ficheiro)
    {
        BufferedImage imagem = null;
        try(InputStream fis = new FileInputStream(ficheiro))
        {
            imagem = ImageIO.read(fis);
        }
        catch (NullPointerException|IOException ex) {}
        
        return imagem;
    }
}
