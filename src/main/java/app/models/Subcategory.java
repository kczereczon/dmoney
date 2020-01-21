package app.models;

import app.core.Model;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Subcategories")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Subcategory extends Model {

    @Column(name = "name")
    @NonNull private String name;

    @OneToOne()
    @JoinColumn(name="category_id")
    @NonNull private Category category;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "subcategory_id", updatable = false)
    private List<Entry> entries;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "subcategory_id", updatable = false)
    private List<Plan> plans;

    public Float getTotal() {
        float total = 0;

        if(entries != null) {
            for (Entry entry : getEntries()
            ) {
                total += entry.getTotal();
            }
        }

        return total;
    }

    public Plan getCurrentPlan() {
        Plan plan = plans.get(plans.size()-1);

        String currentDate = new SimpleDateFormat("MM/yyyy").format(System.currentTimeMillis());
        String planDate = new SimpleDateFormat("MM/yyyy").format(plan.getTrueCreatedAt());

        if(planDate.equals(currentDate)) {
            return plan;
        } else {
            return null;
        }
    }
}
