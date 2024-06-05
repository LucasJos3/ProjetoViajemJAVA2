import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<Destino> cadastrar(@RequestBody Destino destino) {
        Destino novoDestino = destinoService.salvar(destino);
        return new ResponseEntity<>(novoDestino, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Destino> listar() {
        return destinoService.listar();
    }

    @GetMapping("/pesquisar")
    public List<Destino> pesquisar(@RequestParam(required = false) String nome, @RequestParam(required = false) String localizacao) {
        if (nome != null) {
            return destinoService.buscarPorNome(nome);
        } else if (localizacao != null) {
            return destinoService.buscarPorLocalizacao(localizacao);
        } else {
            return List.of();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> detalhar(@PathVariable Long id) {
        return destinoService.buscarPorId(id)
                .map(destino -> new ResponseEntity<>(destino, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/avaliar")
    public ResponseEntity<Destino> avaliar(@PathVariable Long id, @RequestParam int nota) {
        try {
            Destino destinoAvaliacao = destinoService.avaliar(id, nota);
            return new ResponseEntity<>(destinoAvaliacao, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        destinoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
