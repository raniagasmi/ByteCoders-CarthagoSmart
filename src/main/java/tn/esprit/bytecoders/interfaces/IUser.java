package tn.esprit.bytecoders.interfaces;


import tn.esprit.bytecoders.Entity.User;

public interface IUser extends ICrud<User>{
    public User getUserById(int id);
}
