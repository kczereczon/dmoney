package app.models;

import app.core.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Categories")
@NoArgsConstructor
public class Category extends Model {

    public Category (String name) {
        this.name = name;
    }

    @Column(name = "name")
    @Getter @Setter private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "category_id", updatable = false)
    private List<Subcategory> subcategories;

    public Float getTotal() {
        float total = 0;

        if(subcategories != null) {
            for (Subcategory subcategory : subcategories
            ) {
                total += subcategory.getTotal();
            }
        }
        return total;
    }
}
