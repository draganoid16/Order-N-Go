package orderngo;


import orderngo.userinterface.GerenteForm;
import orderngo.userinterface.GestorForm;
import orderngo.userinterface.LoginForm;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author grupo1
 */
public class App
{
    public static void main(String[] args){
        LoginForm loginForm = new LoginForm(null);
    }
    public static void GerenteMenu() throws SQLException, IOException {
        //teste login form e main form
        GerenteForm gerenteForm = new GerenteForm(null, "");
    }

    public static void GestorMenu() throws SQLException {
        //teste login form e main form
        GestorForm gestorForm = new GestorForm(null,"");
    }
}
