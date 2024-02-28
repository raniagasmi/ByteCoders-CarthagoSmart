package ByteCoders.Service;


import ByteCoders.Model.lieuRecyclage;

import java.sql.SQLException;

public interface Recyclage {
    void addLieuRecyclage(lieuRecyclage lieuRecyclage) throws SQLException;

}

