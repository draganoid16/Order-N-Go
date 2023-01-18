package orderngo.testes.utils;

import orderngo.utils.PasswordUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author grupo1
 */
public class PasswordUtilsTest
{
    /**
     * Test of isPasswordValida method, of class PasswordUtils.
     */
    @Test
    public void testIsPasswordValida()
    {
        // password invalida (null)
        char[] password = null;
        boolean expResult = false;
        boolean result = PasswordUtils.isPasswordValida(password);
        
        assertEquals(expResult, result);
        
        // password invalida (vazia)
        password = new char[0];
        expResult = false;
        result = PasswordUtils.isPasswordValida(password);
        
        assertEquals(expResult, result);
        
        // password valida
        password = "teste".toCharArray();
        expResult = true;
        result = PasswordUtils.isPasswordValida(password);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isBCryptHash method, of class PasswordUtils.
     */
    @Test
    public void testIsBCryptHash()
    {
        // hash invalida (null)
        String hash = null;
        boolean expResult = false;
        boolean result = PasswordUtils.isBCryptHash(hash);
        
        assertEquals(expResult, result);
        
        // hash invalida (nao corresponde a uma BCrypt hash)
        hash = "$2q$00$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO";
        expResult = false;
        result = PasswordUtils.isBCryptHash(hash);
        
        assertEquals(expResult, result);
        
        // hash valida
        hash = "$2y$10$zKIfZPJNVjm2/HXHa.PVJe9LnRnbPttz5kUMwvi.Rhum4D5pPd3aO";
        expResult = true;
        result = PasswordUtils.isBCryptHash(hash);
        
        assertEquals(expResult, result);
    }
}
