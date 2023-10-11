package stratego.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import stratego.domain.Stuk;
import stratego.domain.Vak;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "bordstate")
public class BordEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany
    @Column(name = "vakken", unique = true, nullable = false)
    private List<Vak> vakken;

    @Column(name = "xCoordinaat", unique = true, nullable = false)
    private int xCoordinaat;

    @Column(name = "yCoordinaat", unique = true, nullable = false)
    private int yCoordinaat;

    @Column(name = "stuk")
    private Stuk stuk;

    // Default constructor (no-args)
    //JPA requires a default constructor for entities. This constructor is typically empty and used by JPA when creating instances of the entity.
    public BordEntity () {
    }

    // Parameterized constructor
    // Initialize other fields if needed
    public BordEntity (List<Vak> vakken, int xCoordinaat, int yCoordinaat, Stuk stuk) {
        this.vakken = vakken;
        this.xCoordinaat = xCoordinaat;
        this.yCoordinaat = yCoordinaat;
        this.stuk = stuk;
    }

    public Long getId() {
        return id;
    }

    public List<Vak> getVakken() {
        return vakken;
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
