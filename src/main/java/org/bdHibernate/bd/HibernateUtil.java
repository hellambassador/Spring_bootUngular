package org.bdHibernate.bd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try{
//            Configuration configuration = new Configuration();
//            configuration.configure();
//            sessionFactory = configuration.buildSessionFactory();
            Configuration configuration = new Configuration().configure().addAnnotatedClass(ManufacturerEntity.class)
                    .addAnnotatedClass(MaterialEntity.class)
                    .addAnnotatedClass(ByerEntity.class)
                    .addAnnotatedClass(ExpenseEntity.class)
                    .addAnnotatedClass(MastersEntity.class)
                    .addAnnotatedClass(OrdersEntity.class)
                    .addAnnotatedClass(ToolEntity.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }catch (Throwable ex){
            throw ex;
        }
    }
    public static Session getSession() throws Exception{
        return sessionFactory.openSession();
    }
}
