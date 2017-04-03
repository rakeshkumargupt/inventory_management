package com.inventory.user;

import com.inventory.dao.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by rakeshgupta on 4/3/17.
 */
public class UserApp {
    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();


        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

        }catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            System.out.println("In Catach Block");
            System.exit(1);
        }

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

        User user = new User();
        user.setId(14);
        user.setName("Raman");
        user.setPhoneNumber(834758485);
        user.setAddr("Padrauna");

        session.persist(user);
        transaction.commit();
            System.out.println("Code is running..");
        } catch (Exception e) {
        transaction.rollback();
        } finally {
        session.close();
        }
        sessionFactory.close();
    }
}
