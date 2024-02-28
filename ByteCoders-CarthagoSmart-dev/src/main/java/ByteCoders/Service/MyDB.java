package ByteCoders.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class MyDB{
        final String URL = "jdbc:mysql://localhost:3306/carthagosmart";
        final String USER = "root";
        final String pwd = "";
        private Connection cnx;
        private static MyDB instance;

        private MyDB(){
            try {
                cnx =  DriverManager.getConnection(URL, USER, pwd);
                System.out.println("connection established");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public static MyDB getInstance() {
            if (instance == null) {
                instance = new MyDB();
            }

            return instance;
        }

        public Connection getCnx() {
            return cnx;
        }
    }
