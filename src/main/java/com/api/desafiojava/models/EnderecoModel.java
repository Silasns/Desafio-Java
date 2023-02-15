package com.api.desafiojava.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ENDERECOS")
public class EnderecoModel {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "ENDERECO_ID")
    private Long usuarioId;

    @Column
    private String enderecoPrioritario;

    @Column
    private String logradouro;
    @Column
    private String cep;
    @Column
    private String numero;
    @Column
    private String cidade;

    public EnderecoModel(Long id, Long usuarioId, String enderecoPrioritario, String logradouro, String cep, String numero, String cidade) {
        Id = id;
        this.usuarioId = usuarioId;
        this.enderecoPrioritario = enderecoPrioritario;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }

    public EnderecoModel() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEnderecoPrioritario() {
        return enderecoPrioritario;
    }

    public void setEnderecoPrioritario(String enderecoPrioritario) {
        this.enderecoPrioritario = enderecoPrioritario;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
