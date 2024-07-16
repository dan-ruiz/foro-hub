package med.forohub.api.domain.entity.topico;

import java.time.LocalDate;

public record DatosObtenerTopicos(

         String titulo,
         String mensaje,
         LocalDate fechaDeCreacion,
         EstadoTopico estado,
         String autor,
         String curso
) {

    public DatosObtenerTopicos(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstado(),
                topico.getAutor(), topico.getCurso());

    }
}
