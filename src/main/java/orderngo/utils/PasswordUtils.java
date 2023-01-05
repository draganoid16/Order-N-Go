package orderngo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 *
 * @author grupo1
 */
public class PasswordUtils
{
    /* Usar encriptarPassword(char[] password) por motivos de segurança
    https://www.baeldung.com/java-storing-passwords Use char[] Array Over a String for Manipulating Passwords in Java?
    
    @Deprecated
    public static String encriptarPassword(String password)
    {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }
    */
    
    public static String encriptarPassword(char[] password)
    {
        CharBuffer charBuffer = CharBuffer.wrap(password);
        String encriptada = new BCryptPasswordEncoder().encode(charBuffer);
        
        // Limpa o buffer e a password
        charBuffer.clear();
        Arrays.fill(password, '\000');
        
        return encriptada;
    }
    
    public static boolean verificarPassword(char[] password, String encriptada)
    {
        CharBuffer charBuffer = CharBuffer.wrap(password);
        boolean isCorreta = new BCryptPasswordEncoder().matches(charBuffer, encriptada);
        
        // Limpa o buffer e a password
        charBuffer.clear();
        Arrays.fill(password, '\000');
        
        return isCorreta;
    }
}
