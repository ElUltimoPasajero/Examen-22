package models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "ejemplar")
public class Ejemplar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado")
    private String estado; /* excelente, bueno, regular, malo */

    @Column(name = "edicion")
    private Integer edicion;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public Ejemplar() {

    }

    public Ejemplar(String estado, Integer edicion) {
        this.estado = estado;
        this.edicion = edicion;
    }


    @Override
    public String toString() {
        return "Ejemplar{" + "id=" + id + ", estado=" + estado + ", edicion=" + edicion + '}';
    }
    
    
}
