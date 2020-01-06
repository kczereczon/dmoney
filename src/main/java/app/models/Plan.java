package app.models;

import app.core.Model;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "Plans")
@NoArgsConstructor
@AllArgsConstructor
public class Plan extends Model {

    public Plan(Float value, Subcategory subcategory) {
        this.value = value;
        this.subcategory = subcategory;
    }

    @Column(name = "value")
    private Float value;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @Column(name = "description")
    private String description;
}
