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
public class Subcategory extends Model {

    @Column(name = "name")
    @Getter @Setter private String name;

    @OneToOne()
    private Category category;

    @OneToMany
    @JoinColumn(name = "subcategory_id")
    private List<Entry> entries;
}
