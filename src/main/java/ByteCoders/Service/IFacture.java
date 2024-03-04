package ByteCoders.Service;


import ByteCoders.Model.Facture;

import java.sql.SQLException;

public interface IFacture extends ICrud<Facture> {
    public Facture getFactureById(int id) throws SQLException;
}
