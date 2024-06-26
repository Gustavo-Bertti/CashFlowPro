package br.com.fiap.cashflowpro.controller;

import static org.springframework.http.HttpStatus.CREATED;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cashflowpro.model.Movimentacao;
import br.com.fiap.cashflowpro.repository.MovimentacaoRepository;
import jakarta.validation.Valid;



@RestController
@RequestMapping("movimentacao")

public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository repository;

    @GetMapping
    public Page<Movimentacao> index(@RequestParam(required = false) String categoria, @RequestParam(required = false) Integer mes, @PageableDefault(size = 5, sort = "data", direction = Direction.DESC) Pageable pageable ) {
        
        
        if(categoria != null && mes != null){
             return repository.findByCategoriaNomeAndMes(categoria,mes, pageable);
        }
        if(categoria != null){
            return repository.findByCategoriaNome(categoria, pageable);
        }
        if(mes != null){
            return repository.findByMes(mes, pageable);
        }
        // log.info(categoria);
        return repository.findAll(pageable);
    }
    

    @PostMapping
    @ResponseStatus(CREATED)
    public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao){
        return repository.save(movimentacao);
    }
    
}
