package app.core;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public abstract class Repository<T extends Model> {

    @Getter @Setter Session session;
    Class<T> type;

    @SuppressWarnings("unchecked")
    public Repository(Session session) {
        this.session = session;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public T find(int id) {
        getSession().beginTransaction();
        return (T) getSession().load(type, id);
    }

    public T makePersistent(T entity) {
        getSession().getTransaction().begin();
        getSession().save(entity);
        getSession().getTransaction().commit();

        return entity;
    }

    public T update(T entity) {
        getSession().getTransaction().begin();
        //change update at time
        entity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        getSession().update(entity);
        getSession().getTransaction().commit();

        return entity;
    }

    public void remove(T entity) {
        getSession().getTransaction().begin();
        getSession().delete(entity);
        getSession().getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<T> list(Criterion[] criterionArray) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(type);

        criteria.addOrder(Order.desc("id"));
        for (Criterion criterion: criterionArray
             ) {
            criteria.add(criterion);
        }

        return criteria.list();
    }

    public List<T> list() {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(type);

        criteria.addOrder(Order.desc("id"));

        return criteria.list();
    }

}
