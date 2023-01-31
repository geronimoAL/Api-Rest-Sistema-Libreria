package sistemalibreriaapirest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "editoriales", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class Editorial {
    @Id 
    @GeneratedValue(generator="system-uuid")   
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    
}
