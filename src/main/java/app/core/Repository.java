package app.core;


import com.sun.xml.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;

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

    @SuppressWarnings("unchecked")
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

}
