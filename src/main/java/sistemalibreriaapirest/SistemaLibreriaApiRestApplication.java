package sistemalibreriaapirest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sistemalibreriaapirest.domain.Libro;

@SpringBootApplication
public class SistemaLibreriaApiRestApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SistemaLibreriaApiRestApplication.class, args);
    }

//        public CommandLineRunner run (Libro libro){
//            return args->{
//                libro.setTitulo("hola");
//                libro.setAnio(1211);
//                System.out.println(libro.getTitulo());
//            };
//        }
}
