package ByteCoders.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    final String URL ="jdbc:mysql://localhost:3306/PI";
    final String USER = "root";
    final String PWD= "";
    private Connection cnx;
    private static MyDB instance;
    public MyDB(){
        try{
            cnx = DriverManager.getConnection(URL,USER, PWD);
            System.out.println("connexion établie!");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static MyDB getInstance(){
        if (instance == null){
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCnx(){
        return cnx;
    }

}

