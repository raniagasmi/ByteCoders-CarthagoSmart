package ByteCoders.Service;

import ByteCoders.Model.User;

import java.util.Properties;
//import javax.mail.Authenticator;


public class Session {
    static Session instance;
    static User loggedInUser;

    public static Session getInstance(Properties properties/*Authenticator auth*/) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Session()
    {

    }

    public static Session getInstance()
    {
        if(instance == null)
        {
            instance = new Session();
            return instance;
        }
        else
        {
            return instance;
        }
    }

    public static User getLoggedInUser()
    {
        return loggedInUser;
    }

    public static void setLoggedInUser( User LoggedInUser)
    {
        Session.loggedInUser = LoggedInUser;
    }

}
