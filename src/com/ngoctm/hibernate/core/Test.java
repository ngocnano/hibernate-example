package com.ngoctm.hibernate.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("src/main/resources/hibernate.cfg.xml");
        if(file.exists()){
            System.out.println("OK");
        }
        SessionFactory sessionFactory = new Configuration().configure(file)
                .addAnnotatedClass(Customer.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            Customer customer = new Customer("Tu", "Tran", "ngocnano1998@gmail.com");
            session.beginTransaction();
            session.save(customer);
            System.out.println(customer.getId());
            customer.setLastName("Nguyen");
            System.out.println(customer.getId());
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
