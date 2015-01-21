package kz.app.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

	private static SessionFactory buildSessionFactory() {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		System.out.println("Hibernate Configuration loaded");
		ServiceRegistry serviceReg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		System.out.println("Hibernate serviceRegistry created");
		return conf.buildSessionFactory(serviceReg);
	}
	
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}

    public static <T> List<T> createQueryForList(String query) {
        Session session = getSession();
        try {
            session.beginTransaction();
            List<T> list = session.createQuery(query).list();
            session.getTransaction().commit();
            if(list.isEmpty())
                return null;
            return list;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            // Не уверен, что надо открывать/закрывать сессию на каждый запрос. Это долго же
            if(session.isOpen())
                session.close();
        }
    }

}
