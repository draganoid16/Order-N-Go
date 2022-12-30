package orderngo.basedados;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.Blob;

import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author grupo1
 */
public class BaseDadosUtils
{
    public static BufferedImage blobToImage(Blob imagemBlob) throws SQLException
    {
        if (imagemBlob == null)
            return null;
        
        BufferedImage imagem = null;
        try
        {
            imagem = ImageIO.read(imagemBlob.getBinaryStream());
        }
        catch (IOException ioe) {}
        
        imagemBlob.free();
        return imagem;
    }

    public static String encriptarPassword(String password)
    {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }
}
