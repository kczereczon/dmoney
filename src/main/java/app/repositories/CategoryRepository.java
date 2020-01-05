package app.repositories;

import app.core.Repository;
import app.models.Category;
import org.hibernate.Session;

public class CategoryRepository extends Repository<Category> {
    public CategoryRepository(Session session) {
        super(session);
    }
}
