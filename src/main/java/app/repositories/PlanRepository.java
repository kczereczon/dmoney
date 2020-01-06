package app.repositories;

import app.core.Repository;
import app.models.Plan;
import org.hibernate.Session;

public class PlanRepository extends Repository<Plan> {
    public PlanRepository(Session session) {
        super(session);
    }
}
