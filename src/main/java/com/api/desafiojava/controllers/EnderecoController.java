package com.api.desafiojava.controllers;

import com.api.desafiojava.dtos.EnderecoDto;
import com.api.desafiojava.models.EnderecoModel;
import com.api.desafiojava.models.PessoaModel;
import com.api.desafiojava.services.EnderecoService;
import com.api.desafiojava.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin( origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/endereco")
public class EnderecoController {

    final EnderecoService enderecoService;
    final PessoaService pessoaService;
    public EnderecoController(EnderecoService enderecoService, PessoaService pessoaService) {
        this.enderecoService = enderecoService;
        this.pessoaService = pessoaService;
    }


    @PostMapping("/{id}")
    public ResponseEntity<Object> criarEndereco(@PathVariable(value = "id") Long id, @RequestBody @Valid EnderecoDto enderecoDto){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }

        var pessoaModel = new PessoaModel();
        var enderecoModel = new EnderecoModel();

        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setUsuarioId(id);
        EnderecoModel enderecoSalvo = enderecoService.save(enderecoModel);
        pessoaModel.adicionar(enderecoSalvo);


        //EnderecoModel enderecoSalvo = enderecoService.save(enderecoModel);
        //pessoaModel.setEndereco(enderecoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retornaEnderco(@PathVariable(value = "id") Long id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get().getEndereco());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> atualizaEnderecoPrioritario(@PathVariable(value = "id") Long id, @RequestBody EnderecoDto enderecoDto){
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.findById(id);
        if (!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrada.");
        }
        EnderecoModel enderecoModel = enderecoService.atualizar(id, enderecoDto);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(enderecoModel));
    }
}
