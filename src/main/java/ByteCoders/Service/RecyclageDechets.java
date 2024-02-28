package ByteCoders.Service;


import ByteCoders.Model.lieuRecyclage;
import ByteCoders.Service.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RecyclageDechets {
    Connection cnx;
    public RecyclageDechets() {cnx = MyDB.getInstance().getCnx();}
    public void addLieuRecyclage(lieuRecyclage lieuRecyclage) throws SQLException {
        String req = "INSERT INTO lieurecyclage (nom) VALUES (?)";
        PreparedStatement stm = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, lieuRecyclage.getPointRecyclage());
        stm.executeUpdate();
    }
    }
