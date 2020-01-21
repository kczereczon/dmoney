package app.repositories;

import app.core.Repository;
import app.models.Category;
import org.hibernate.Session;

import java.util.List;

public class CategoryRepository extends Repository<Category> {
    public CategoryRepository(Session session) {
        super(session);
    }
}
