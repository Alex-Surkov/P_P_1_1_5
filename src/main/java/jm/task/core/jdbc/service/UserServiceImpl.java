package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoHibernateImpl();

    public void createUsersTable() throws HibernateException {
        dao.createUsersTable();
    }

    public void dropUsersTable() throws HibernateException {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws HibernateException {
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws HibernateException {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() throws HibernateException {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() throws HibernateException {
        dao.cleanUsersTable();
    }
}
