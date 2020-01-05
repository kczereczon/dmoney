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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Subcategory> subcategories;
}
