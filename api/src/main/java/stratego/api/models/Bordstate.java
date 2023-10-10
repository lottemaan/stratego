package stratego.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "bordstate")
public class Bordstate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "xCoordinaat", unique = true, nullable = false)
    private int xCoordinaat;

    @Column(name = "yCoordinaat", unique = true, nullable = false)
    private int yCoordinaat;

    @Column(name = "Stuk")
    private Stuk stuk;

    // Default constructor (no-args)
    //JPA requires a default constructor for entities. This constructor is typically empty and used by JPA when creating instances of the entity.
    public Bordstate() {
    }

    // Parameterized constructor
    // Initialize other fields if needed
    public Bordstate(int xCoordinaat, int yCoordinaat, Stuk stuk) {
        this.xCoordinaat = xCoordinaat;
        this.yCoordinaat = yCoordinaat;
        this.stuk = stuk;
    }

    public Long getId() {
        return id;
    }

    public int getXCoordinaat() {
        return xCoordinaat;
    }

    public int getYCoordinaat() {
        return yCoordinaat;
    }

    public Stuk getStuk() {
        return stuk;
    }
}
