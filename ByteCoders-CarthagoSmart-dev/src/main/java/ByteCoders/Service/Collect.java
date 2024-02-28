package ByteCoders.Service;

import java.sql.SQLException;
import java.util.List;
import ByteCoders.Model.collect;
public interface Collect <collect>{
    void addDechets(collect Dechets) throws SQLException;
    collect getTypeDechetsById(int typeId) throws SQLException;
    //List<collect> getAllTypesDechets() throws SQLException;
    void updateDechets(collect Dechets) throws SQLException;
    void deleteDechets(int typeId) throws SQLException;
    //void addCollecte(collect collect) throws SQLException;
    //void addLieuCollecte(collect collect) throws SQLException;
    //void addDateCollecte(collect collect) throws SQLException;

}
