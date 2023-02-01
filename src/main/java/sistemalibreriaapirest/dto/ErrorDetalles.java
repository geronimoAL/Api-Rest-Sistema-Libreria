package sistemalibreriaapirest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class ErrorDetalles {
    private Date marcaDeTiempo;
	private String mensaje;
	private String detalles;
}
