package ByteCoders.Service;


import ByteCoders.Model.User;

public interface IUser extends ICrud<User> {
    public User getUserById(int id);
}
