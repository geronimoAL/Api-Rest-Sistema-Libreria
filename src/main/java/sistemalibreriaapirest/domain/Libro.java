
package sistemalibreriaapirest.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "libros", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class Libro implements Serializable {
    
    @Id 
    @GeneratedValue(generator="system-uuid")   
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "anio")
    private String anio;
    @OneToOne(fetch= FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="autor_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Autor autor;
    @OneToOne(fetch= FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="editorial_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Editorial editorial;
    
}
