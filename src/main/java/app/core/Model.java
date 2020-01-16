package app.core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@MappedSuperclass
@Getter @Setter
public abstract class Model {

    public Model() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public String getCreatedAt() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(createdAt);
    }

    public String getUpdatedAt() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(updatedAt);
    }
}
