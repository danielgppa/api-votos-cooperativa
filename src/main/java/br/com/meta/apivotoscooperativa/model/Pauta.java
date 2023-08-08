package br.com.meta.apivotoscooperativa.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O título não pode ser vazio.")
    @NotNull(message = "O título não pode ser vazio.")
    @Size(min = 3, max = 30, message = "O título deve ter entre 3 e 30 caracteres.")
    private String titulo;

    @Size(max = 1000, message = "A descrição não pode ter mais de 1000 caracteres.")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
