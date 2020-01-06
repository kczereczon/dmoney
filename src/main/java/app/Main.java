package app;

import app.models.Category;
import app.models.Entry;
import app.models.Subcategory;
import app.repositories.CategoryRepository;
import app.repositories.EntryRepository;
import app.repositories.SubcategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {

    public static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();

            Session session = factory.openSession();
            SubcategoryRepository subcategoryRepository = new SubcategoryRepository(session);
            Subcategory subcategory = subcategoryRepository.find(1);

            EntryRepository entryRepository = new EntryRepository(session);
            entryRepository.makePersistent(new Entry("Paliwo rzeszów", 5.01f, 11, subcategory, false));

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
