package med.forohub.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Respuesta {

    private Long id;
    private String nombre;
    private String mensaje;
    private String topico;
    private String fechaDeCreacion;
    private String autor;
    private String solucion;
}
