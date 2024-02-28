package tn.esprit.bytecoders.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> {
    public void create(T t) throws SQLException;

    public List<T> getAll() throws SQLException;

    public void update(T t) throws SQLException;

    public boolean delete(T t) throws SQLException;
}
