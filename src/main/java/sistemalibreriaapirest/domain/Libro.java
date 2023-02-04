
package sistemalibreriaapirest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "libros", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class Libro  implements Serializable{
    
    @Id 
    @GeneratedValue(generator="system-uuid")   
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "anio")
    private String anio;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="autor_id")
    private Autor autor;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="editorial_id")
    private Editorial editorial;
    
}
