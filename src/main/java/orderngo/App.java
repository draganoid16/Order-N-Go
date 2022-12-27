package orderngo;


import orderngo.userinterface.GestorForm;
import orderngo.userinterface.LoginForm;
import orderngo.userinterface.GerenteForm;

/**
 * @author grupo1
 */
public class App {
    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
    }
    public static void GerenteMenu(){
        //teste login form e main form
        GerenteForm gerenteForm = new GerenteForm(null);
    }

    public static void GestorMenu(){
        //teste login form e main form
        GestorForm gestorForm = new GestorForm(null);
    }
}
