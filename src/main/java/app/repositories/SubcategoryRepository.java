package app.repositories;

import app.core.Repository;
import app.models.Subcategory;
import org.hibernate.Session;

public class SubcategoryRepository extends Repository<Subcategory> {
    public SubcategoryRepository(Session session) {
        super(session);
    }
}
