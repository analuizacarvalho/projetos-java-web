package business.cliente;

import java.util.ArrayList;
import java.util.List;
import business.repository.ClienteRepository;
import entity.Pessoa;
import entity.PessoaFisica;
import entity.PessoaJuridica;
import entity.PessoaJuridicaTelefone;
import entity.dto.ClienteDTO;
import entity.dto.FiltroBuscaClienteDTO;
import entity.dto.PessoaJuridicaTelefoneDTO;
import entity.types.TipoClienteEnum;


public class ClienteBusiness {
	
	private ClienteRepository clienteRepository = new ClienteRepository();
	
	public List<ClienteDTO> listarCliente(FiltroBuscaClienteDTO filtro) {
		ArrayList<ClienteDTO> listaClientes = new ArrayList<>();
		
		if(filtro.getTipo() == null || TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(filtro.getTipo())) {
		    listaClientes.addAll(clienteRepository.consultarPessoaFisica(filtro));
		}
		
		if(filtro.getTipo() == null || TipoClienteEnum.PESSOA_JURIDICA.getDescricao().equals(filtro.getTipo())) {
		    listaClientes.addAll(clienteRepository.consultarPessoaJuridica(filtro));
		}

		return listaClientes;
	}
	
	public ClienteDTO buscarCliente(ClienteDTO clienteSelecionado) {
		ClienteDTO cliente = new ClienteDTO();
		if(TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(clienteSelecionado.getTipo())) {
			PessoaFisica pessoaFisica = clienteRepository.find(PessoaFisica.class, clienteSelecionado.getId());
			cliente = new ClienteDTO(pessoaFisica);
		}
		 
		if(TipoClienteEnum.PESSOA_JURIDICA.getDescricao().equals(clienteSelecionado.getTipo())) {
			PessoaJuridica pessoaJuridica = clienteRepository.find(PessoaJuridica.class, clienteSelecionado.getId());
			cliente = new ClienteDTO(pessoaJuridica);
		}
		return cliente;
	}
	
	public void salvarCliente(ClienteDTO clienteSelecionado) {
		if(TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(clienteSelecionado.getTipo())) {
			salvarPessoaFisica(clienteSelecionado);
		}
		 
		if(TipoClienteEnum.PESSOA_JURIDICA.getDescricao().equals(clienteSelecionado.getTipo())) {
			salvarPessoaJuridica(clienteSelecionado);

		}
	}
	
	private void salvarPessoaFisica(ClienteDTO clientePessoaFisicaDTO) {
		PessoaFisica pessoaFisica;
		
		if(clientePessoaFisicaDTO.getId() == null) {
			pessoaFisica = new PessoaFisica();
		} else {
			pessoaFisica = clienteRepository.find(PessoaFisica.class, clientePessoaFisicaDTO.getId());
		}
		
		pessoaFisica.setCpf(clientePessoaFisicaDTO.getCpf());
		pessoaFisica.setDataNascimento(clientePessoaFisicaDTO.getDataNascimento());
		pessoaFisica.setEmail(clientePessoaFisicaDTO.getEmail());
		pessoaFisica.setNome(clientePessoaFisicaDTO.getNome());
		pessoaFisica.setTelefone(clientePessoaFisicaDTO.getTelefone());
		clienteRepository.merge(pessoaFisica);
	}
	
	private void salvarPessoaJuridica(ClienteDTO clientePessoaJuridicaDTO) {
		PessoaJuridica pessoaJuridica;
		
		if(clientePessoaJuridicaDTO.getId() == null) {
			pessoaJuridica = new PessoaJuridica();
		} else {
			pessoaJuridica = clienteRepository.find(PessoaJuridica.class, clientePessoaJuridicaDTO.getId());
		}
		
		pessoaJuridica.setCnpj(clientePessoaJuridicaDTO.getCnpj());
		pessoaJuridica.setNomeFantasia(clientePessoaJuridicaDTO.getNomeFantasia());
		pessoaJuridica.setRazaoSocial(clientePessoaJuridicaDTO.getRazaoSocial());
		pessoaJuridica.setSite(clientePessoaJuridicaDTO.getSite());
		
		PessoaJuridica pessoaJuridicaSalva = clienteRepository.merge(pessoaJuridica);
		atualizaTelefones(clientePessoaJuridicaDTO, pessoaJuridicaSalva);
	}

	private void atualizaTelefones(ClienteDTO clientePessoaJuridicaDTO, PessoaJuridica pessoaJuridicaSalva) {
		PessoaJuridica pessoaJuridica = clienteRepository.find(PessoaJuridica.class, pessoaJuridicaSalva.getIdPessoa());
		pessoaJuridica.getTelefones().removeIf(t -> clientePessoaJuridicaDTO.getTelefones().stream().noneMatch(uDTO -> uDTO.getIdPessoaTelefone().equals(t.getIdPessoaTelefone())));
		
		for(PessoaJuridicaTelefoneDTO pessoaJuridicaTelefoneDTO : clientePessoaJuridicaDTO.getTelefones()) {
			PessoaJuridicaTelefone telefone = pessoaJuridica.getTelefones().stream().filter(t -> t.getIdPessoaTelefone() != null && t.getIdPessoaTelefone().equals(pessoaJuridicaTelefoneDTO.getIdPessoaTelefone())).findFirst().orElse(null);
			if(telefone == null) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(pessoaJuridica.getIdPessoa());
				telefone = new PessoaJuridicaTelefone();
				telefone.setPessoa(pessoa);
				pessoaJuridica.getTelefones().add(telefone);
			}
			telefone.setTelefone(pessoaJuridicaTelefoneDTO.getTelefone());
		}
		clienteRepository.merge(pessoaJuridica);
	}
	
	public void removerCliente(ClienteDTO clienteSelecionado) {
		if(TipoClienteEnum.PESSOA_FISICA.getDescricao().equals(clienteSelecionado.getTipo())) {
			clienteRepository.remove(PessoaFisica.class, clienteSelecionado.getId());
		}
		 
		if(TipoClienteEnum.PESSOA_JURIDICA.getDescricao().equals(clienteSelecionado.getTipo())) {
			clienteRepository.remove(PessoaJuridica.class, clienteSelecionado.getId());
		}
	}

}
