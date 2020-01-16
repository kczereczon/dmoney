package app.models;

import app.core.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Entries")
@NoArgsConstructor
@AllArgsConstructor
public class Entry extends Model {

    public Entry (String name, Float price, Integer quantity, Subcategory subcategory, Boolean isIncome) {
        this.name = name;
        this.subcategory = subcategory;
        this.value = price;
        this.quantity = quantity;
        this.isIncome = isIncome;
    }

    @Column(name = "name")
    @Getter @Setter private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @Column(name = "value")
    private Float value;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "is_income")
    private Boolean isIncome;

    public String getSubcategoryName() {
        return subcategory.getCategory().getName() + " - " + subcategory.getName();
    }

    public Float getTotal() {
        return value * quantity;
    }

    public String getDirection() {
        return (isIncome) ? "In" : "Out";
    }
}
