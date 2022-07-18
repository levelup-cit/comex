package br.com.alura.comex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.modelo.CategoriaEntity;

//Por ser interface não necessita anotação
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

	Page<CategoriaEntity> findAllById(Long id, Pageable paginacao);

	Page<CategoriaEntity> findAll(Pageable paginacao);

}
