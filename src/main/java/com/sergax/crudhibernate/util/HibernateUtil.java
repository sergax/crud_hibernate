package com.sergax.crudhibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final String HIBERNATE_PROPERTIES = "hibernate.cfg.xml";

    static {
        try {
            sessionFactory = new Configuration().configure(HIBERNATE_PROPERTIES).buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
