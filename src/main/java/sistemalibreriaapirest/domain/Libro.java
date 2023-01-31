
package sistemalibreriaapirest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "libros", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class Libro {
    
    @Id 
    @GeneratedValue(generator="system-uuid")   
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "anio")
    private String anio;
    
}
