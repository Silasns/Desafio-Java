package com.api.desafiojava.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PESSOAS")
public class PessoaModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column
    private String dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ENDERECO_ID")
    private List<EnderecoModel> enderecos = new ArrayList<>();
    //Resolver BO pra chave primaria

    public PessoaModel() {

    }

    public PessoaModel(Long id, String nome, String dataNascimento, List<EnderecoModel> endereco) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EnderecoModel> getEndereco() {
        return enderecos;
    }

    public void setEndereco(List<EnderecoModel> endereco) {
        this.enderecos = endereco;
    }

    public void adicionar(EnderecoModel endereco){
        this.enderecos.add(endereco);
    }
}
