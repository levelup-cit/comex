package br.com.alura.comex.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.modelo.CategoriaEntity;
import br.com.alura.comex.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired(required = true)
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public Page<CategoriaDto> listar(@RequestParam(required = false) Long id, Pageable paginacao) {

		if (id != null) {
			Page<CategoriaEntity> categorias = categoriaRepository.findAllById(id, paginacao);
			return CategoriaDto.converter(categorias);
		}

		Page<CategoriaEntity> categorias = categoriaRepository.findAll(paginacao);
		// TODO validacao
		return CategoriaDto.converter(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable Long id) {
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);

		if (categoria.isPresent())
			return ResponseEntity.ok(new CategoriaDto(categoria.get()));

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> inserir(@RequestBody @Valid CategoriaForm categoriaForm,
			UriComponentsBuilder uriBuilder) {
		CategoriaEntity categoria = categoriaForm.converter();
		categoriaRepository.save(categoria);

		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid CategoriaForm categoriaForm) {
		Optional<CategoriaEntity> optional = categoriaRepository.findById(id);

		if (optional.isPresent()) {
			CategoriaEntity categoria = categoriaForm.atualizar(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<CategoriaEntity> optional = categoriaRepository.findById(id);

		if (optional.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
