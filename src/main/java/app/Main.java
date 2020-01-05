package app;

import app.models.Category;
import app.models.Subcategory;
import app.repositories.CategoryRepository;
import app.repositories.SubcategoryRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {

    public static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

//        CategoryRepository categoryRepository = new CategoryRepository(factory.openSession());
//        System.out.println(categoryRepository.find(5));

        SubcategoryRepository subcategoryRepository = new SubcategoryRepository(factory.openSession());
//        subcategoryRepository.makePersistent(new Subcategory("Paliwo", category));
        Subcategory subcategory = subcategoryRepository.find(1);
        subcategory.setName("Paliwo");
        subcategoryRepository.update(subcategory);

    }
}
