import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public Destino salvar(Destino destino) {
        return destinoRepository.save(destino);
    }

    public List<Destino> listar() {
        return destinoRepository.findAll();
    }

    public List<Destino> buscarPorNome(String nome) {
        return destinoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Destino> buscarPorLocalizacao(String localizacao) {
        return destinoRepository.findByLocalizacaoContainingIgnoreCase(localizacao);
    }

    public Optional<Destino> buscarPorId(Long id) {
        return destinoRepository.findById(id);
    }

    public void excluir(Long id) {
        destinoRepository.deleteById(id);
    }

    public Destino avaliar(Long id, int nota) {
        Destino destino = destinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        double novaMedia = ((destino.getAvaliacaoMedia() * destino.getNumeroAvaliacoes()) + nota) / (destino.getNumeroAvaliacoes() + 1);
        destino.setAvaliacaoMedia(novaMedia);
        destino.setNumeroAvaliacoes(destino.getNumeroAvaliacoes() + 1);
        return destinoRepository.save(destino);
    }
}
