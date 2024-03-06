package br.com.fiap.cashflowpro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.cashflowpro.model.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());
    List<Categoria> repository = new ArrayList<>();

    @GetMapping
    public List<Categoria> index() {
        return repository;
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        repository.add(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        log.info("buscando categoria com id {}", id);

        // stream
        var categoria = repository.stream().filter(c -> c.id().equals(id)).findFirst();

        log.info("categoria encontrada: {}", categoria);

        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(categoria.get());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        var categoria =  getCategoriaById(id);

        log.info("categoria encontrada: {}", categoria);

        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.remove(categoria.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Categoria categoria ) {
        var encontrada =  getCategoriaById(id);
        if (encontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Categoria attCateroia = new Categoria(encontrada.get().id(), categoria.nome(), categoria.icone());
        log.info("atualizando categoria com id: {} para: {}", encontrada.get().id(),attCateroia );
        
        repository.remove(encontrada.get());
        repository.add(attCateroia);

        return ResponseEntity.ok().build();
    }

    private Optional<Categoria> getCategoriaById(Long id) {
        var encontrada = repository.stream().filter(c -> c.id().equals(id)).findFirst();
        return encontrada;
    }
}
