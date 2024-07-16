package med.forohub.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import med.forohub.api.domain.entity.topico.DatosActualizarTopico;
import med.forohub.api.domain.entity.topico.DatosGuardarTopico;
import med.forohub.api.domain.entity.topico.DatosObtenerTopicos;
import med.forohub.api.domain.entity.topico.Topico;
import med.forohub.api.domain.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Controller de Topicos", description = "Permite registrar, eliminar, listar y actualizar")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Operation(summary = "Nos permite agregar un topico", description = "Enviar topico sin id")
    public ResponseEntity<?> guardarTopico(@RequestBody @Valid DatosGuardarTopico datosTopico,
                                           UriComponentsBuilder uriBuilder) {
        Topico topico = new Topico(datosTopico);
        topicoService.guardarTopicoSiNoEsDuplicado(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosObtenerTopicos(topico));
    }

    @GetMapping
    @Parameter(description = "Nos permite obtener una lista de todos los topicos.")
    public ResponseEntity<?> listarTopicos(){
        List<DatosObtenerTopicos> topicos = topicoService.listarTopicos();

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    @Parameter(description = "Nos permite obtener un topico por id.")
    public ResponseEntity<?> obtenerTopicoId(@PathVariable Long id){
        var topico = topicoService.obtenerTopicoPorId(id);

        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Parameter(description = "Nos permite actualizar un topico por id")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosTopico) {
        DatosActualizarTopico datosTopicoConId = new DatosActualizarTopico(
                id,
                datosTopico.titulo(),
                datosTopico.mensaje(),
                datosTopico.autor(),
                datosTopico.curso()
        );
        topicoService.actualizarTopico(new Topico(datosTopicoConId));
        return ResponseEntity.ok("Topico Actualizado");
    }

    @DeleteMapping("/{id}")
    @Parameter(description = "Nos permite eliminar un topico por id")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);

        return ResponseEntity.noContent().build();
    }


}
