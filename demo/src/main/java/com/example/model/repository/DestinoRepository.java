package com.seuprojeto.viagens.repository;

import com.seuprojeto.viagens.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByNomeContainingIgnoreCase(String nome);
    List<Destino> findByLocalizacaoContainingIgnoreCase(String localizacao);
}
