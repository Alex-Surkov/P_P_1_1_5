package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.Column;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sqlCreateTable = "CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeLargeUpdate(sqlCreateTable);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public void dropUsersTable() {
        String sqlDropTable = "DROP TABLE IF EXISTS `users`";
        try (Connection connection = Util.getConnection();
             Statement dropStatement = connection.createStatement()) {
            dropStatement.execute(sqlDropTable);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserQuery = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(saveUserQuery)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public void removeUserById(long id) {
        String saveUserQuery = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(saveUserQuery)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String getAllUsersQuery = "SELECT * FROM users";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(getAllUsersQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanAllUsersQuery = "DELETE FROM users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(cleanAllUsersQuery);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
