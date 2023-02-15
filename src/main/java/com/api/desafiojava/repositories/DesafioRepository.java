package com.api.desafiojava.repositories;

import com.api.desafiojava.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesafioRepository extends JpaRepository<PessoaModel, Long> {
}
