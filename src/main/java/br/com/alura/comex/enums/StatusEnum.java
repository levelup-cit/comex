package br.com.alura.comex.enums;

public enum StatusEnum {
	ATIVA, 
	INATIVA;

	private String status;

	private StatusEnum() {
	}

	public String getStatus() {
		return this.status;
	}
}
