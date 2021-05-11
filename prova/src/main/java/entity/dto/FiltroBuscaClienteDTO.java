package entity.dto;

import java.io.Serializable;

public class FiltroBuscaClienteDTO implements Serializable {

	private static final long serialVersionUID = -5673800497496233717L;
	
	private String tipo;
	private String nome;
	private String cpf;
	private String razaSocial;
	private String nomeFantasia;
	private String cnpj;
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public String getRazaSocial() {
		return razaSocial;
	}
	
	public void setRazaSocial(String razaSocial) {
		this.razaSocial = razaSocial;
	}

}
