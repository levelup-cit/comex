package br.com.alura.comex.infra.categoria;

import br.com.alura.comex.dominio.categoria.CategoriaDto;
import br.com.alura.comex.dominio.categoria.CategoriaForm;
import br.com.alura.comex.dominio.categoria.Categoria;
import br.com.alura.comex.dominio.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public List<CategoriaDto> listaDeCategorias() {
    List<Categoria> listaDeCategorias = categoriaRepository.findAll();
    return CategoriaDto.converter(listaDeCategorias);
  }


  @PostMapping
  @Transactional
  public ResponseEntity<CategoriaDto> inserirCategoria(@RequestBody @Valid CategoriaForm form,
                                                       UriComponentsBuilder uriBuilder) {

    Categoria categoria = form.converter(categoriaRepository);
    categoriaRepository.save(categoria);

    URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
    return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
  }

  @PutMapping("{id}")
  @Transactional
  public ResponseEntity<CategoriaDto> alteraStatusDaCategoria(@PathVariable Long id,
                                                              @RequestBody @Valid CategoriaForm form) {

    Optional<Categoria> buscaCategoria = categoriaRepository.findById(id);

    if(buscaCategoria.isPresent()){
      Categoria categoria = form.atualizar(id, categoriaRepository);
      return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    return ResponseEntity.notFound().build();
  }


}
