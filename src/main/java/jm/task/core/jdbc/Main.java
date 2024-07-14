package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Василий", "Иванов", (byte) 5);
        userServiceImpl.saveUser("Гасилий", "Иванов", (byte) 4);
        userServiceImpl.saveUser("Басилий", "Иванов", (byte) 3);
        userServiceImpl.saveUser("Сасилий", "Иванов", (byte) 2);
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.removeUserById(1L);
        userServiceImpl.removeUserById(1L);


    }
}
