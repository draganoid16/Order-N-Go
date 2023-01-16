package orderngo.testes.utilizador;

import orderngo.utilizador.Utilizador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.sql.SQLException;
import orderngo.utils.PasswordUtils;

/**
 *
 * @author grupo1
 */
public class UtilizadorTest
{
    private Utilizador instance;
    
    @BeforeEach
    public void init()
    {
        instance = new UtilizadorImpl("utilizador@uti.com", "utilizador teste", "111111111", "morada teste");
    }
       
    
    //<editor-fold defaultstate="collapsed" desc="testCamposFinal">
    /**
     * Test of constructor and getEmail method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testGetEmail()
    {
        // email valido
        String expResult = "utilizador@uti.com";
        String result = instance.getEmail();
        
        assertEquals(expResult, result);
        
        // email invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String email = null;
            instance = new UtilizadorImpl(email, "utilizador teste", "111111111", "morada teste");
        });
        
        // email invalido (nao corresponde a email)
        assertThrows(IllegalArgumentException.class, () -> {
            String email = "nao,email@email.com";
            instance = new UtilizadorImpl(email, "utilizador teste", "111111111", "morada teste");
        });
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testSet">
    /**
     * Test of setNome method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetNome()
    {
        // nome valido
        String expResult = "utilizador teste";
        String result = instance.getNome();
        assertEquals(expResult, result);
        
        // nome invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = null;
            instance.setNome(nome);
        });
        
        // nome invalido (vazio)
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = "";
            instance.setNome(nome);
        });
    }
    
    /**
     * Test of setTelemovel method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetTelemovel()
    {
        // telemovel valido
        String expResult = "111111111";
        String result = instance.getTelemovel();
        assertEquals(expResult, result);
        
        // telemovel invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String telemovel = null;
            instance.setTelemovel(telemovel);
        });
        
        // telemovel invalido (nao corresponde a telemovel)
        assertThrows(IllegalArgumentException.class, () -> {
            String telemovel = "a1a1a1a1a";
            instance.setTelemovel(telemovel);
        });
    }

    /**
     * Test of setMorada method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetMorada()
    {
        // morada valido
        String expResult = "morada teste";
        String result = instance.getMorada();
        assertEquals(expResult, result);
        
        // nome invalido (null)
        assertThrows(IllegalArgumentException.class, () -> {
            String morada = null;
            instance.setMorada(morada);
        });
        
        // nome invalido (vazio)
        assertThrows(IllegalArgumentException.class, () -> {
            String morada = "";
            instance.setMorada(morada);
        });
    }

    /**
     * Test of setPasswordEncriptada method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetPasswordEncriptada()
    {
        // passwordEncriptada valida
        String passwordEncriptada = "$2y$10$2dzh/8yAuoea5O.aPJmuPezqD7p72GbPzp3OPAy3L9d9kLyKPXMeC";
        String expResult = passwordEncriptada;
        instance.setPasswordEncriptada(passwordEncriptada);
        
        String result = instance.getPasswordEncriptada();
        assertEquals(expResult, result);
        
        // passwordEncriptada null
        passwordEncriptada = null;
        expResult = null;
        instance.setPasswordEncriptada(passwordEncriptada);
        
        result = instance.getPasswordEncriptada();
        assertEquals(expResult, result);
        
        // passwordEncriptada invalida (nao corresponde BCrypt hash)
        assertThrows(IllegalArgumentException.class, () -> {
            String passEncriptada = "$2g$ad$2dzh/8yAuoea5O.aPJmuPezqD7p72GbPzp3OPAy3L9d9kLyKPXMeC";
            instance.setPasswordEncriptada(passEncriptada);
        });
    }

    /**
     * Test of setPassword method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("ThrowableResultIgnored")
    public void testSetPassword()
    {
        // password valida
        char[] password = "teste".toCharArray();
        instance.setPassword(password);
        
        password = "teste".toCharArray();
        String passwordEncriptada = instance.getPasswordEncriptada();
        assertTrue(PasswordUtils.verificarPassword(password, passwordEncriptada));
        
        // password invalida (null)
        assertThrows(IllegalArgumentException.class, () -> {
            char[] pass = null;
            instance.setPassword(pass);
        });
        
        // password invalida (vazia)
        assertThrows(IllegalArgumentException.class, () -> {
            char[] pass = new char[0];
            instance.setPassword(pass);
        });
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="testEquals">
    /**
     * Test of equals method, of class Utilizador.
     */
    @Test
    public void testEquals()
    {
        // equals
        EqualsVerifier.simple().forClass(Utilizador.class)
            .suppress(
                Warning.NULL_FIELDS,
                Warning.STRICT_HASHCODE,
                Warning.ALL_FIELDS_SHOULD_BE_USED
            )
            .verify();
    }
    //</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="UtilizadorImpl">
    public class UtilizadorImpl extends Utilizador
    {
        public UtilizadorImpl(String email, String nome, String telemovel, String morada)
        {
            super(email, nome, telemovel, morada);
        }

        @Override
        public void save() throws SQLException {}

        @Override
        public void delete() throws SQLException {}
    }
    //</editor-fold>
}
