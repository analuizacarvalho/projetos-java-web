package business.repository;

import java.util.List;

import javax.persistence.Query;

import entity.dto.ClienteDTO;
import entity.dto.FiltroBuscaClienteDTO;
import utils.JPAUtil;
import utils.StringUtils;

public class ClienteRepository extends GenericRepository {
	
	public List<ClienteDTO> consultarPessoaFisica(FiltroBuscaClienteDTO filtro) {
		entityManager = new JPAUtil().getEntityManager();

		Query query = entityManager.createQuery(getQueryConsultaPessoaFisica(filtro));
		popularParametrosBuscaPessoaFisica(query, filtro);
		
		List<ClienteDTO> lista = query.getResultList();
		
		entityManager.close();
		
		return lista;
	}
	
	private String getQueryConsultaPessoaFisica(FiltroBuscaClienteDTO filtro) {
		StringBuilder hql = new  StringBuilder();
		hql.append("SELECT new entity.dto.ClienteDTO(p) FROM PessoaFisica p WHERE 1=1 ");
		
		if(filtro.getNome() != null) {
			hql.append(" AND LOWER(nome) LIKE :nome ");
		}
		
		if(filtro.getCpf() != null) {
			hql.append(" AND cpf LIKE :cpf ");
		}
		
		return hql.toString();
	}
	
	private void popularParametrosBuscaPessoaFisica(Query query, FiltroBuscaClienteDTO filtro) {
		if(filtro.getNome() != null) {
			query.setParameter("nome", "%" + filtro.getNome().toLowerCase() + "%");
		}
		
		if(filtro.getCpf() != null) {
			query.setParameter("cpf", "%" + StringUtils.removerMascaraNumero(filtro.getCpf()) + "%");
		}
	}
	
	public List<ClienteDTO> consultarPessoaJuridica(FiltroBuscaClienteDTO filtro) {
		entityManager = new JPAUtil().getEntityManager();
	
		Query query = entityManager.createQuery(getQueryConsultaPessoaJuridica(filtro));
		popularParametrosBuscaPessoaJuridica(query, filtro);
		
		List<ClienteDTO> lista = query.getResultList();
		
		entityManager.close();
		
		return lista;
	}
	
	private String getQueryConsultaPessoaJuridica(FiltroBuscaClienteDTO filtro) {
		StringBuilder hql = new  StringBuilder();
		hql.append("SELECT new entity.dto.ClienteDTO(p) FROM PessoaJuridica p WHERE 1=1 ");
		
		if(filtro.getNomeFantasia() != null) {
			hql.append(" AND LOWER(nomeFantasia) LIKE :nomeFantasia ");
		}
		
		if(filtro.getRazaSocial() != null) {
			hql.append(" AND LOWER(razaoSocial) LIKE :razaoSocial ");
		}
		
		if(filtro.getCnpj() != null) {
			hql.append(" AND cnpj LIKE :cnpj ");
		}
		return hql.toString();
	}
	
	private void popularParametrosBuscaPessoaJuridica(Query query, FiltroBuscaClienteDTO filtro) {
		if(filtro.getNomeFantasia() != null) {
			query.setParameter("nomeFantasia", "%" + filtro.getNomeFantasia().toLowerCase() + "%");
		}
		
		if(filtro.getRazaSocial() != null) {
			query.setParameter("razaoSocial", "%" + filtro.getRazaSocial().toLowerCase() + "%");
		}
		
		if(filtro.getCnpj() != null) {
			query.setParameter("cnpj", "%" + StringUtils.removerMascaraNumero(filtro.getCnpj()) + "%");
		}
	}

}
