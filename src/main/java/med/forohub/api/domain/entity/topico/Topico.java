package med.forohub.api.domain.entity.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaDeCreacion;
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;
    private String autor;
    private String curso;
    private String respuestas;

    public Topico(DatosGuardarTopico datosGuardarTopico) {
        this.titulo = datosGuardarTopico.titulo();
        this.mensaje = datosGuardarTopico.mensaje();
        this.fechaDeCreacion = LocalDate.now(); // Asigna la fecha y hora actual
        this.estado = EstadoTopico.PENDIENTE;
        this.autor = datosGuardarTopico.autor();
        this.curso = datosGuardarTopico.curso();
        // Asumiendo que 'respuestas' debe ser inicializado a un valor por defecto o nulo
        this.respuestas = null;
    }

    public Topico(DatosActualizarTopico datosActualizarTopico) {
        this.id = datosActualizarTopico.Id();
        this.titulo = datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
        this.fechaDeCreacion = LocalDate.now(); // Asigna la fecha y hora actual
        this.estado = EstadoTopico.PENDIENTE;
        this.autor = datosActualizarTopico.autor();
        this.curso = datosActualizarTopico.curso();
        // Asumiendo que 'respuestas' debe ser inicializado a un valor por defecto o nulo
        this.respuestas = null;
    }
}
