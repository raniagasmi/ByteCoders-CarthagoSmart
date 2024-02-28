package esprit.tn.Service;

import esprit.tn.Entity.Recyclage.lieuRecyclage;

import java.sql.SQLException;

public interface Recyclage {
    void addLieuRecyclage(lieuRecyclage lieuRecyclage) throws SQLException;

}

