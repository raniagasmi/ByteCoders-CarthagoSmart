package tn.esprit.bytecoders.interfaces;


import tn.esprit.bytecoders.Entity.Facture;

import java.sql.SQLException;

public interface IFacture extends ICrud<Facture> {
    public Facture getFactureById(int id) throws SQLException;
}
