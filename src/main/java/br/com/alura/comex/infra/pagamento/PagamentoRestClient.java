package br.com.alura.comex.infra.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@FeignClient(url = "${pagamento.servico.url}", name = "pagamentos")
public interface PagamentoRestClient {

  @PostMapping("/pagamentos")
  ResponseEntity<PagamentoDto> cria(@RequestBody PagamentoDto pagamento);

}
