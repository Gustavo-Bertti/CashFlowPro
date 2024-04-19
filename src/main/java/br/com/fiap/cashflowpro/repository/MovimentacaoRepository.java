package br.com.fiap.cashflowpro.repository;




import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.cashflowpro.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    org.springframework.data.domain.Page<Movimentacao>  findByCategoriaNome(String categoria, Pageable pageable);

    //JPQL
    @Query("SELECT m FROM Movimentacao m WHERE MONTH(m.data) = ?1")
    org.springframework.data.domain.Page<Movimentacao> findByMes(Integer mes, Pageable pageable);

    
    @Query("SELECT m FROM Movimentacao m WHERE m.categoria.nome = ?1 AND MONTH(m.data) = ?2 ")
    org.springframework.data.domain.Page<Movimentacao>  findByCategoriaNomeAndMes(String categoria, Integer mes, Pageable pageable);

    Page<Movimentacao> findAll(Pageable pageable);

    
    
}
