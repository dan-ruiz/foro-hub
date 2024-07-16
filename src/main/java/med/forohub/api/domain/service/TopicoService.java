package med.forohub.api.domain.service;

import med.forohub.api.domain.entity.topico.DatosObtenerTopicos;
import med.forohub.api.domain.entity.topico.Topico;
import med.forohub.api.domain.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public boolean existeTopicoDuplicado(String titulo, String mensaje) {
        return topicoRepository.findByTituloAndMensaje(titulo, mensaje).isPresent();
    }

    // Método para guardar un tópico, si no es duplicado
    public void guardarTopicoSiNoEsDuplicado(Topico topico) {
        if (!existeTopicoDuplicado(topico.getTitulo(), topico.getMensaje())) {
            topicoRepository.save(topico);
        } else {
            throw new RuntimeException("El tópico ya existe.");
        }
    }

    public List<DatosObtenerTopicos> listarTopicos(){
        List<Topico> topicos = topicoRepository.findAll();
        List<DatosObtenerTopicos> datosTopicos = topicos.stream()
                .map(t -> new DatosObtenerTopicos(t.getTitulo(), t.getMensaje(), t.getFechaDeCreacion(), t.getEstado(),
                        t.getAutor(), t.getCurso()))
                .collect(Collectors.toList());

        return datosTopicos;
    }

    public DatosObtenerTopicos obtenerTopicoPorId(Long id){
        Optional<Topico> topicoObtenido = topicoRepository.findById(id);

        if(topicoObtenido.isPresent()){
            Topico topico = topicoObtenido.get();
            DatosObtenerTopicos datosTopico = new DatosObtenerTopicos(topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaDeCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
            return datosTopico;
        }else {
            throw new RuntimeException("El tópico no existe.");
        }
    }

    public void actualizarTopico(Topico topico){
        Optional<Topico> topicoObtenido = topicoRepository.findById(topico.getId());

        if(topicoObtenido.isPresent()){
            topicoRepository.save(topico);
        } else {
            throw new RuntimeException("El tópico no existe.");
        }

    }

    public void eliminarTopico(Long id){
        Optional<Topico> topicoObtenido = topicoRepository.findById(id);

        if(topicoObtenido.isPresent()){
            topicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("El tópico no existe.");
        }
    }

}
