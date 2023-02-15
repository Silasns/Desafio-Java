package com.api.desafiojava.services;

import com.api.desafiojava.models.PessoaModel;
import com.api.desafiojava.repositories.DesafioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    DesafioRepository desafioRepository;

    public PessoaService(DesafioRepository desafioRepository) {
        this.desafioRepository = desafioRepository;
    }

    @Transactional
    public PessoaModel save(PessoaModel pessoaModel) {
        return desafioRepository.save(pessoaModel);
    }

    public List<PessoaModel> findAll() {
        return desafioRepository.findAll();
    }

    public Optional<PessoaModel> findById(Long id) {
        return desafioRepository.findById(id);
    }
    @Transactional
    public void delete(PessoaModel pessoaModel) {
        desafioRepository.delete(pessoaModel);
    }
}
