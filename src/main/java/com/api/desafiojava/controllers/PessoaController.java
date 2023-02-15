package com.api.desafiojava.controllers;

import com.api.desafiojava.dtos.PessoaDto;
import com.api.desafiojava.models.EnderecoModel;
import com.api.desafiojava.models.PessoaModel;
import com.api.desafiojava.services.EnderecoService;
import com.api.desafiojava.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin( origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/pessoa")
public class PessoaController {
    final PessoaService pessoaService;
    final EnderecoService enderecoService;
    public PessoaController(PessoaService pessoaService, EnderecoService enderecoService) {
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Object> criarPessoa(@RequestBody @Valid PessoaDto pessoaDto){
        var pessoaModel = new PessoaModel();
        var enderecoModel = new EnderecoModel();
        //enderecoModel.setUsuarioId(pessoaModel.getId());

        BeanUtils.copyProperties(pessoaDto.getEndereco(), enderecoModel);
        BeanUtils.copyProperties(pessoaDto, pessoaModel);

        pessoaModel = pessoaService.save(pessoaModel);
        enderecoModel.setUsuarioId(pessoaModel.getId());
        EnderecoModel enderecoSalvo = enderecoService.save(enderecoModel);
        pessoaModel.adicionar(enderecoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaModel);
    }
    @GetMapping
    public ResponseEntity<List<PessoaModel>> retornaTodasPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> retornaUmaPessoaPorId(@PathVariable(value = "id") Long id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPessoa(@PathVariable(value = "id") Long id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaPessoa(@PathVariable(value = "id") Long id, @RequestBody @Valid PessoaDto pessoaDto){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setId(pessoaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));
    }

    
}
