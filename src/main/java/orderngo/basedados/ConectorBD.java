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
    private static ConectorBD instance = null;
    private static final String PROPERTIES_FILE = "basededados.properties";
    
    private final Connection con;
    private final String urlCon;
    
    //<editor-fold defaultstate="collapsed" desc="getProperties">
    public static Properties getDefaultProperties()
    {
        // Properties por omissão
        Properties prop = new Properties();
        prop.setProperty("url", "jdbc:mysql://localhost/database");
        prop.setProperty("user", "root");
        prop.setProperty("password", "password");
        prop.setProperty("autoReconnect", "false");
        prop.setProperty("maxReconnects", "3");
        prop.setProperty("initialTimeout", "2");
        
        return prop;
    }
    
    private static Properties getFileProperties()
    {
        Properties prop = getDefaultProperties();
        
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
            catch (IOException ignored) {}
        }
        catch (IOException ignored) {}

        return prop;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Construtores">
    protected ConectorBD(Properties prop) throws SQLException
    {
        urlCon = prop.getProperty("url");
        
        con = DriverManager.getConnection(
            urlCon,
            prop
        );
    }
    private ConectorBD() throws SQLException
    {
        this(getFileProperties());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Instance">
    public static ConectorBD getInstance() throws SQLException
    {
        if (instance == null)
            setInstance(new ConectorBD());

        return instance;
    }
    
    public static void setInstance(ConectorBD instance)
    {
        ConectorBD.instance = instance;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Statements">
    public ResultSet executeQuery(String sql) throws SQLException
    {
        var st = con.createStatement();
        st.closeOnCompletion();
        
        return st.executeQuery(sql);
    }
    
    public int executeUpdate(String sql) throws SQLException
    {
        var st = con.createStatement();
        st.closeOnCompletion();
        
        return st.executeUpdate(sql);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Prepared Statements">
    public PreparedStatement prepareStatement(String sql) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.closeOnCompletion();
        
        return ps;
    }
    
    public CallableStatement prepareCall(String sql) throws SQLException
    {
        CallableStatement cs = con.prepareCall(sql);
        cs.closeOnCompletion();
        
        return cs;
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
    //</editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="equals/hashCode/toString">
    public boolean canEqual(Object obj) 
    {
        return (obj instanceof ConectorBD);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        
        if (!(obj instanceof ConectorBD other))
            return false;

        
        if (!other.canEqual(this))
            return false;
        
        return urlCon.equals(other.urlCon);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + urlCon.hashCode();
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ConectorBD{");
        sb.append("urlCon=").append(urlCon);
        sb.append('}');
        return sb.toString();
    }
    //</editor-fold>
}
