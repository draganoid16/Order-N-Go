package orderngo.basedados;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * @author grupo1
 */
public class ConectorBD
{
    private static final String PROPERTIES_FILE = "basededados.properties";
    private static Properties getProperties()
    {
        // Properties por omissão
        Properties prop = new Properties();
        prop.setProperty("url", "jdbc:mysql://localhost/database");
        prop.setProperty("user", "root");
        prop.setProperty("password", "password");
        prop.setProperty("autoReconnect", "false");
        prop.setProperty("maxReconnects", "3");
        prop.setProperty("initialTimeout", "2");
        
        try(FileInputStream fis = new FileInputStream(PROPERTIES_FILE))
        {
            prop.load(fis);
        }
        catch (FileNotFoundException fnfe) // se não existir, cria-o  
        {
            try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE))
            {
                prop.store(fos, "Propriedades da ligacao a base de dados");
            }
            catch (IOException ex) {}
        }
        catch (IOException ex) {}

        return prop;
    }
    
    
    private Connection con = null;
    private ConectorBD() throws SQLException
    {
        Properties prop = getProperties();
        
        con = DriverManager.getConnection(
            prop.getProperty("url"),
            prop
        );
    }
    
            
    public ResultSet executeQuery(String sql) throws SQLException
    {
        return con.createStatement().executeQuery(sql);
    }
    
    public int executeUpdate(String sql) throws SQLException
    {
        return con.createStatement().executeUpdate(sql);
    }
    
    
    public PreparedStatement prepareStatement(String sql) throws SQLException
    {
        return con.prepareStatement(sql);
    }
    
    public CallableStatement prepareCall(String sql) throws SQLException
    {
        return con.prepareCall(sql);
    }
    

    public boolean executePrepared(PreparedStatement statement) throws SQLException
    {
        return statement.execute();
    }
    
    public ResultSet executePreparedQuery(PreparedStatement statement) throws SQLException
    {
        return statement.executeQuery();
    }
        
    public int executePreparedUpdate(PreparedStatement statement) throws SQLException
    {
        return statement.executeUpdate();
    }
            
    
    private static ConectorBD instance = null;
    public static ConectorBD getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new ConectorBD();
        }
            
        return instance;
    }
}
