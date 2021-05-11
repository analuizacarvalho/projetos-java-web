package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica_telefone", schema = "public")
public class PessoaJuridicaTelefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa_telefone")
	private Long idPessoaTelefone;
	
	@Column(name = "telefone")
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	public Long getIdPessoaTelefone() {
		return idPessoaTelefone;
	}
	
	public void setIdPessoaTelefone(Long idPessoaTelefone) {
		this.idPessoaTelefone = idPessoaTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}