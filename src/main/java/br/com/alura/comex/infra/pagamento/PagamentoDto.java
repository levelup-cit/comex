package br.com.alura.comex.infra.pagamento;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class PagamentoDto {

  private Long id;
  private BigDecimal valor;
  private String cpfCliente;
  private Long pedidoId;

  public PagamentoDto() {}

  public PagamentoDto(Long id, BigDecimal valor, String cpfCliente, Long pedidoId) {
    this.id = id;
    this.valor = valor;
    this.cpfCliente = cpfCliente;
    this.pedidoId = pedidoId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public String getCpfCliente() {
    return cpfCliente;
  }

  public void setCpfCliente(String cpfCliente) {
    this.cpfCliente = cpfCliente;
  }

  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
  }
}
