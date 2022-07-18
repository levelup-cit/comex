package br.com.alura.comex.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private long cpf;
	private String telefone;
	
	@OneToOne
	private UsuarioEntity usuario;
	
	// @ManyToOne
	@Embedded
	private EnderecoEntity endereco;

	public ClienteEntity() {
	}

	public ClienteEntity(String nome, long cpf, String telefone, String rua, String numero, String complemento,
			String bairro, String cidade, String estado) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = new EnderecoEntity(rua, numero, complemento, bairro, cidade, estado);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	// TODO implementar validação do cpf
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public long getId() {
		return id;
	}
}
