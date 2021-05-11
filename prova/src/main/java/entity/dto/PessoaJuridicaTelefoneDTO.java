package entity.dto;

import java.io.Serializable;
import java.util.UUID;

import entity.PessoaJuridicaTelefone;
import utils.StringUtils;

public class PessoaJuridicaTelefoneDTO implements Serializable {

	private static final long serialVersionUID = 58641295841061907L;
	
	private String id;
	
	private Long idPessoaTelefone;
	private String telefone;
	
	public PessoaJuridicaTelefoneDTO() {
		id = UUID.randomUUID().toString();
	}
	
	public PessoaJuridicaTelefoneDTO(PessoaJuridicaTelefone pessoaJuridicaTelefone) {
		this.id = UUID.randomUUID().toString();
		this.idPessoaTelefone = pessoaJuridicaTelefone.getIdPessoaTelefone();
		this.telefone = pessoaJuridicaTelefone.getTelefone();
	}
	
	public String telefoneComMascara() {
	    return StringUtils.aplicarMascaraTelefone(this.telefone);
	}
	
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
		this.telefone = StringUtils.removerMascaraNumero(telefone);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
