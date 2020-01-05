package app.models;

import app.core.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "Subcategories")
@NoArgsConstructor
public class Subcategory extends Model {

    public Subcategory (String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Subcategory (String name) {
        this.name = name;
    }

    @Column(name = "name")
    @Getter @Setter private String name;

    @OneToOne()
    private Category category;
}
