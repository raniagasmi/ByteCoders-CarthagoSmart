package bytecoders.pidev.utils;

import java.sql.*;

public class MyDB {
    final String URL = "jdbc:mysql://localhost:3306/PiDev";
    final String USER = "root";
    final String pwd = "";

    private static MyDB instance;
    private Connection cnx;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(URL, USER, pwd);
            System.out.println("Connection established");
        } catch (SQLException ex) {
            System.out.println("Error while connecting to the database: " + ex.getMessage());
            // Vous pouvez également renvoyer l'exception pour une gestion ultérieure
            // throw new RuntimeException("Error while connecting to the database", ex);
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
