package dao;

import dao.util.JpaUtil;
import interfaces.IBaseDao;
import interfaces.IBaseModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDao<T extends IBaseModel> implements IBaseDao<T> {


    private Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        persistenceClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public void salvar(T entity) {
        EntityManager manager = JpaUtil.getEntityManager();

        try {
            manager.getTransaction().begin();
            if (entity.getId() == null) {
                manager.persist(entity);
            } else {
                manager.merge(entity);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    @Override
    public void alterar(T entity) {
        EntityManager manager = JpaUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }


    }

    @Override
    public void excluir(T entity) {
        EntityManager manager = JpaUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.remove(manager.find(entity.getClass(), entity.getId()));
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }


    @Override
    public T buscarPorid(Integer id) {
        EntityManager manager = JpaUtil.getEntityManager();
        try {
            T resultado = manager.find(persistenceClass, id);
            return resultado;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<T> buscarTodos() {
        EntityManager manager = JpaUtil.getEntityManager();
        TypedQuery<T> query = manager
                .createQuery("from " + persistenceClass.getSimpleName(), persistenceClass);
        List<T> resultado = query.getResultList();
        manager.close();
        return resultado;
    }

}
