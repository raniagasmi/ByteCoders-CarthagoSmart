package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    final String URL ="jdbc:mysql://localhost:3306/JDBC_test";
    final String USER ="root";
    final String PWD ="";

    private Connection connection;

    private static MyDB instance;
    public MyDB(){
        try {
            connection = DriverManager.getConnection(URL,USER,PWD) ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MyDB getInstance(){
        if(instance == null){
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCnx(){
        return connection;
    }

    public Connection getConnection() {
    return getCnx();}

    public MyDB prepareStatement(String s) {return null;
    }

    public void setString(int i, String nomEvent) {
    }

    public void executeUpdate() {
    }

}
