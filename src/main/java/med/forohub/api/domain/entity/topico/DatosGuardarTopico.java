package med.forohub.api.domain.entity.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosGuardarTopico(
        @NotBlank(message = "El campo titulo no debe estar vacío")
        String titulo,
        @NotBlank(message = "El campo mensaje no debe estar vacío")
        String mensaje,
        @NotBlank(message = "El campo autor no debe estar vacío")
        String autor,
        @NotBlank(message = "El campo curso no debe estar vacío")
        String curso
) {
}
