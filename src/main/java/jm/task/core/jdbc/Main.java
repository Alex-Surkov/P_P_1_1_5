package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Василий", "Иванов", (byte) 5);
        userService.saveUser("Гасилий", "Иванов", (byte) 4);
        userService.saveUser("Басилий", "Иванов", (byte) 3);
        userService.saveUser("Сасилий", "Иванов", (byte) 2);
        System.out.println(userService.getAllUsers());


    }
}
