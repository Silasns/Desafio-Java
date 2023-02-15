package com.api.desafiojava.services;

import com.api.desafiojava.dtos.EnderecoDto;
import com.api.desafiojava.models.EnderecoModel;
import com.api.desafiojava.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoModel save(EnderecoModel enderecoModel) {
        return enderecoRepository.save(enderecoModel);
    }

    public Optional<EnderecoModel> findById(Long Id) {
        return enderecoRepository.findById(Id);
    }
    @Transactional
    public EnderecoModel atualizar(Long id, EnderecoDto enderecoDto) {
        EnderecoModel enderecoModel = enderecoRepository.getOne(id);
        if (enderecoDto.getEnderecoPrioritario() != null) enderecoModel.setEnderecoPrioritario(enderecoDto.getEnderecoPrioritario());
        if (enderecoDto.getLogradouro() != null) enderecoModel.setLogradouro(enderecoDto.getLogradouro());
        if (enderecoDto.getCep() != null) enderecoModel.setCep(enderecoDto.getCep());
        if (enderecoDto.getNumero() != null) enderecoModel.setNumero(enderecoDto.getNumero());
        if (enderecoDto.getCidade() != null) enderecoModel.setCidade(enderecoDto.getCidade());

        return enderecoModel;

    }
}
