package orderngo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 *
 * @author grupo1
 */
public final class PasswordUtils
{
    private PasswordUtils() {}
    
    /* Usar encriptarPassword(char[] password) por motivos de segurança
    https://www.baeldung.com/java-storing-passwords 
    "Use char[] Array Over a String for Manipulating Passwords in Java?"
    
    @Deprecated
    public static String encriptarPassword(String password)
    {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }
    */
    
    public static String encriptarPassword(char[] password)
    {
        if (!isPasswordValida(password))
            throw new IllegalArgumentException("Password invalida!");
        
        CharBuffer charBuffer = CharBuffer.wrap(password);
        String encriptada = new BCryptPasswordEncoder().encode(charBuffer);
        
        // Limpa o buffer e a password
        charBuffer.clear();
        Arrays.fill(password, '\000');
        
        return encriptada;
    }
    
    public static boolean verificarPassword(char[] password, String encriptada)
    {
        if (!isPasswordValida(password))
            throw new IllegalArgumentException("Password invalida!");
        
        if (!isBCryptHash(encriptada))
            throw new IllegalArgumentException("Password encriptada nao e uma BCrypt hash!");
        
        CharBuffer charBuffer = CharBuffer.wrap(password);
        boolean isCorreta = new BCryptPasswordEncoder().matches(charBuffer, encriptada);
        
        // Limpa o buffer e a password
        charBuffer.clear();
        Arrays.fill(password, '\000');
        
        return isCorreta;
    }
    
    public static boolean isBCryptHash(String str)
    {
        if (str == null)
            return false;
        
        return str.matches("^\\$2[ayb]\\$.{56}$");
    }
    
    public static boolean isPasswordValida(char[] password)
    {
        if (password == null)
            return false;
        
        return password.length > 0;
    }
}
