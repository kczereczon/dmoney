package app.repositories;

import app.core.Repository;
import app.models.Entry;
import org.hibernate.Session;

public class EntryRepository extends Repository<Entry> {
    public EntryRepository(Session session) {
        super(session);
    }
}
