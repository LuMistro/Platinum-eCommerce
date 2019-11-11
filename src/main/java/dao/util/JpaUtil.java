package dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private final static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("Platinum-PU");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
