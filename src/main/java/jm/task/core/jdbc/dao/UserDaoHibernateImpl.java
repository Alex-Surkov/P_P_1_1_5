package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        } finally { // Имеет ли смысл так перестраховываться от connection leak?
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            // do i need to remove replace depricated NativeQuery
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        } finally { // Имеет ли смысл так перестраховываться от connection leak?
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        } finally { // Имеет ли смысл так перестраховываться от connection leak?
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user!= null) {
                session.remove(user);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        } finally { // Имеет ли смысл так перестраховываться от connection leak?
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        } finally { // Имеет ли смысл так перестраховываться от connection leak?
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw e;
        } finally { // Имеет ли смысл так перестраховываться от connection leak?
            session.close();
        }
    }
}
