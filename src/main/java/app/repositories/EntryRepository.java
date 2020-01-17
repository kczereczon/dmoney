package app.repositories;

import app.core.Repository;
import app.models.Entry;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EntryRepository extends Repository<Entry> {
    public EntryRepository(Session session) {
        super(session);
    }

    public List<Entry> listToday() {
        Timestamp startDay = new Timestamp(atStartOfDay(new Date(System.currentTimeMillis())));
        Timestamp endDay = new Timestamp(atEndOfDay(new Date(System.currentTimeMillis())));

        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Entry.class);

        criteria.add(Restrictions.between("createdAt",
            startDay,
            endDay
        ));

        criteria.addOrder(Order.desc("id"));

        return criteria.list();
    }

    public long atEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    public long atStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
