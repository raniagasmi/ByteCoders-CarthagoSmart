package ByteCoders.Service;


import ByteCoders.Model.UserMolka;

public interface IUser extends ICrud<UserMolka> {
    public UserMolka getUserById(int id);
}
