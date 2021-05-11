package entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import entity.PessoaFisica;
import entity.PessoaJuridica;
import entity.types.TipoClienteEnum;
import utils.StringUtils;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -1527686674247302759L;
	
	private Long id;
	private String nome;
	private String razaoSocial;
	private String nomeFantasia;
    private String cnpj;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String site;
	private String tipo;
	private String telefone;
	private List<PessoaJuridicaTelefoneDTO> telefones = new ArrayList<>();
	
	public ClienteDTO() {}
	
	public ClienteDTO(PessoaFisica pessoaFisica) {
		this.id = pessoaFisica.getIdPessoa();
		this.tipo = TipoClienteEnum.PESSOA_FISICA.getDescricao();
		this.nome = pessoaFisica.getNome();
		this.cpf = pessoaFisica.getCpf();
		this.dataNascimento = pessoaFisica.getDataNascimento();
		this.email = pessoaFisica.getEmail();
		this.telefone = pessoaFisica.getTelefone();
	}
	
	public ClienteDTO(PessoaJuridica pessoaJuridica) {
		this.id = pessoaJuridica.getIdPessoa();
		this.tipo = TipoClienteEnum.PESSOA_JURIDICA.getDescricao();
		this.razaoSocial = pessoaJuridica.getRazaoSocial();
		this.nomeFantasia = pessoaJuridica.getNomeFantasia();
		this.cnpj = pessoaJuridica.getCnpj();
		this.site = pessoaJuridica.getSite();
		this.telefones = pessoaJuridica.getTelefones().stream().map(t -> new PessoaJuridicaTelefoneDTO(t)).collect(Collectors.toList());
	}
	
	public String nomeCliente() {
		return TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(this.tipo) ? this.nome : this.nomeFantasia;
	}
	
	public String documentoCliente() {
		return TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(this.tipo) ? this.cpf : this.cnpj;
	}
	
	public String documentoComMascara() {
		return TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(this.tipo) ? StringUtils.aplicarMascaraCPF(this.cpf) : StringUtils.aplicarMascaraCNPJ(this.cnpj);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
		this.cnpj = StringUtils.removerMascaraNumero(cnpj);
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = StringUtils.removerMascaraNumero(cpf);
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = StringUtils.removerMascaraNumero(telefone);
	}
	
	public List<PessoaJuridicaTelefoneDTO> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(List<PessoaJuridicaTelefoneDTO> telefones) {
		this.telefones = telefones;
	}

}
