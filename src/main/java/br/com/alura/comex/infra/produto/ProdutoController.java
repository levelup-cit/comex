package br.com.alura.comex.infra.produto;

import br.com.alura.comex.dominio.produto.ProdutoDto;
import br.com.alura.comex.dominio.produto.ProdutoForm;
import br.com.alura.comex.dominio.produto.Produto;
import br.com.alura.comex.dominio.categoria.CategoriaRepository;
import br.com.alura.comex.dominio.produto.ProdutoRepository;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.alura.comex.infra.pagamento.PagamentoDto;
import br.com.alura.comex.infra.pagamento.PagamentoRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  CategoriaRepository categoriaRepository;

  //Coloquei em produto porque não tinha feito a desejável de pedido,
  // a ideia é apenas ver se funciona a integração (farei com kafka tbm se der tempo)
  @Autowired
  private PagamentoRestClient pagamentoRestClient;

  @GetMapping
  public List<ProdutoDto> listaDeProdutos() {
    Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "nome"));
    Page<Produto> listaDeProdutos = produtoRepository.findAll(pageable);

    return ProdutoDto.converter(listaDeProdutos);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ProdutoDto> inserirProduto(@RequestBody @Valid ProdutoForm form,
                                                   UriComponentsBuilder uriBuilder) {

    Produto produto = form.converter(categoriaRepository);
    produtoRepository.save(produto);

    pagamentoRestClient.cria(PagamentoDto.builder()
            .pedidoId(2l)
            .cpfCliente("111.111.111-22")
            .valor(BigDecimal.valueOf(2000l))
            .build());

    URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
    return ResponseEntity.created(uri).body(new ProdutoDto(produto));
  }

}
