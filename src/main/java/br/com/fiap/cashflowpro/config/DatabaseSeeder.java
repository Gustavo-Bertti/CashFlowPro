package br.com.fiap.cashflowpro.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.cashflowpro.model.Categoria;
import br.com.fiap.cashflowpro.model.Movimentacao;
import br.com.fiap.cashflowpro.repository.CategoriaRepository;
import br.com.fiap.cashflowpro.repository.MovimentacaoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Override
    public void run(String... args) throws Exception {
        categoriaRepository.saveAll(List.of(
            Categoria.builder().id(1L).nome("transporte").icone("bus").build(),
            Categoria.builder().id(2L).nome("comida").icone("apple").build(),
            Categoria.builder().id(3L).nome("educação").icone("book").build()
        ));

        movimentacaoRepository.saveAll(List.of(
            Movimentacao.builder().id(1L).descricao("Mc donalds").valor(new BigDecimal(500)).data(LocalDate.now()).tipo("DESPESA").categoria(categoriaRepository.findById(2L).get()).build(),
            Movimentacao.builder().id(2L).descricao("Curso").valor(new BigDecimal(50)).data(LocalDate.now().minusDays(1).minusMonths(1)).tipo("DESPESA").categoria(categoriaRepository.findById(3L).get()).build(),
            Movimentacao.builder().id(3L).descricao("Trem").valor(new BigDecimal(10)).data(LocalDate.now().minusDays(2)).tipo("DESPESA").categoria(categoriaRepository.findById(1L).get()).build()
        ));

    }
    
}
