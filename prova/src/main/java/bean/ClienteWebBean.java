package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import business.cliente.ClienteBusiness;
import entity.dto.ClienteDTO;
import entity.dto.FiltroBuscaClienteDTO;
import entity.dto.PessoaJuridicaTelefoneDTO;
import entity.types.TipoClienteEnum;

@ViewScoped
@ManagedBean(name = "clienteWebBean")
public class ClienteWebBean {

	private List<ClienteDTO> clientes = new ArrayList<>();
	
	private ClienteDTO clienteSelecionado;
	
	private PessoaJuridicaTelefoneDTO telefoneSelecionadoDTO;
	
	private ClienteBusiness clienteBusiness = new ClienteBusiness();
	
	private FiltroBuscaClienteDTO filtro = new FiltroBuscaClienteDTO();
	
	@PostConstruct
	public void init() {
		buscarClientes();		
	}
	
	public void buscarClientes() {
		clientes = clienteBusiness.listarCliente(filtro);
	}
	
	public void removerCliente() {
		clienteBusiness.removerCliente(clienteSelecionado);
        this.clienteSelecionado = null;
        filtro = new FiltroBuscaClienteDTO();
        buscarClientes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente removido com sucesso!"));
        PrimeFaces.current().ajax().update("form:messages", "form");
	}
	
	public void salvarCliente() {
		clienteBusiness.salvarCliente(clienteSelecionado);
        this.clienteSelecionado = null;
        filtro = new FiltroBuscaClienteDTO();
        buscarClientes();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente salvo com sucesso!"));
        PrimeFaces.current().executeScript("PF('modalCadastroCliente').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form");
	}
	
	public void salvarTelefone() {
		PessoaJuridicaTelefoneDTO telefoneSemEdicao = clienteSelecionado.getTelefones().stream().filter(e -> e.getId().equals(telefoneSelecionadoDTO.getId())).findFirst().orElse(null);
		
		if(telefoneSemEdicao == null) {
			clienteSelecionado.getTelefones().add(telefoneSelecionadoDTO);
		} else {
			preencherTelefoneEditado(telefoneSemEdicao);
		}
        telefoneSelecionadoDTO = null;
        PrimeFaces.current().executeScript("PF('modalCadastroTelefone').hide()");
        PrimeFaces.current().ajax().update("adicionarClienteForm:tabelaTelefone");
	}
	
	private void preencherTelefoneEditado(PessoaJuridicaTelefoneDTO telefoneSemEdicao) {
		telefoneSemEdicao.setTelefone(telefoneSelecionadoDTO.getTelefone());
	}
	
	public void removerTelefone(PessoaJuridicaTelefoneDTO pessoaJuridicaTelefoneDTO) {
		clienteSelecionado.getTelefones().remove(pessoaJuridicaTelefoneDTO);
	}
	
	public void editarTelefone(PessoaJuridicaTelefoneDTO pessoaJuridicaTelefoneDTO) {
		telefoneSelecionadoDTO = pessoaJuridicaTelefoneDTO;
	}
	
	public void abrirModalCadastro() {
		clienteSelecionado = new ClienteDTO();
	}
	
	public void abrirModalCadastroTelefone() {
		telefoneSelecionadoDTO = new PessoaJuridicaTelefoneDTO();
	}
	
	public void abrirModalEdicao(ClienteDTO clienteDTO) {
		clienteSelecionado = clienteBusiness.buscarCliente(clienteDTO);
	}
	
	public boolean renderizarPainelPessoaFisica() {
		return clienteSelecionado != null && TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(this.clienteSelecionado.getTipo());
	}
	
	public boolean renderizarPainelPessoaJuridica() {
		return clienteSelecionado != null && TipoClienteEnum.PESSOA_JURIDICA.getDescricao().equals(this.clienteSelecionado.getTipo());
	}
	
	public boolean renderizarPainelBuscaPessoaFisica() {
		return TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(this.filtro.getTipo());
	}
	
	public boolean renderizarPainelBuscaPessoaJuridica() {
		return TipoClienteEnum.PESSOA_JURIDICA.getDescricao().equals(this.filtro.getTipo());
	}
	
	public boolean desabilitarBotaoSalvar() {
		return clienteSelecionado == null || this.clienteSelecionado.getTipo() == null;
	}
	
	public List<ClienteDTO> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<ClienteDTO> clientes) {
		this.clientes = clientes;
	}
	
	public ClienteDTO getClienteSelecionado() {
		return clienteSelecionado;
	}
	
	public void setClienteSelecionado(ClienteDTO clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	public PessoaJuridicaTelefoneDTO getTelefoneSelecionadoDTO() {
		return telefoneSelecionadoDTO;
	}
	
	public void setTelefoneSelecionadoDTO(PessoaJuridicaTelefoneDTO telefoneSelecionadoDTO) {
		this.telefoneSelecionadoDTO = telefoneSelecionadoDTO;
	}
	
	public FiltroBuscaClienteDTO getFiltro() {
		return filtro;
	}
	
	public void setFiltro(FiltroBuscaClienteDTO filtro) {
		this.filtro = filtro;
	}

}
