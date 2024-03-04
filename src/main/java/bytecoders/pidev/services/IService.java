package bytecoders.pidev.services;


import bytecoders.pidev.models.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
public interface IService<T> {

    void addUser(T t) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;
    void updateUser(T t) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    boolean updateUserNewPass(User user) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    void updateUserByCin(User user) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;

    void deleteUser(int id) throws SQLException;
    List<T> recuperer() throws SQLException;

   // User getById(int id)  throws SQLException;
    User Login(String username, String password) throws SQLException;
    void EnvoieMail(String addEmail);

    void deleteUserCin(int id) throws SQLException;



    }
