package app.models;

import app.core.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Subcategories")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Subcategory extends Model {

    @Column(name = "name")
    @NonNull @Getter @Setter private String name;

    @OneToOne()
    @NonNull private Category category;


    @OneToMany
    @JoinColumn(name = "subcategory_id")
    private List<Entry> entries;

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
}
