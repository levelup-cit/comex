package br.com.alura.comex.application.controller.form;

import br.com.alura.comex.domain.Categoria;
import br.com.alura.comex.application.model.enuns.StatusCategoria;
import br.com.alura.comex.application.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class AtualizacaoCategoriaForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private StatusCategoria status = StatusCategoria.ATIVA;

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        categoria.get().setNome(this.nome);
        return categoria.get();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public void setStatus(StatusCategoria status) {
        this.status = status;
    }
}
