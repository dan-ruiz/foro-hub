package med.forohub.api.domain.entity.topico;
import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(

        Long Id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {

        public DatosActualizarTopico setId(Long id) {
                return new DatosActualizarTopico(id, this.titulo, this.mensaje, this.autor, this.curso);
        }
}
