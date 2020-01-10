package app;


import app.models.Entry;
import app.models.Subcategory;
import app.repositories.CategoryRepository;
import app.repositories.EntryRepository;
import app.repositories.PlanRepository;
import app.repositories.SubcategoryRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@Getter
public class DatabaseController {

    public SessionFactory factory;
    public Session session;

    public CategoryRepository categoryRepository;
    public EntryRepository entryRepository;
    public SubcategoryRepository subcategoryRepository;
    public PlanRepository planRepository;

    public DatabaseController() {
        try {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
            session = factory.openSession();

            //initialize repositories
            categoryRepository = new CategoryRepository(session);
            entryRepository = new EntryRepository(session);
            subcategoryRepository = new SubcategoryRepository(session);
            planRepository = new PlanRepository(session);

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


}
