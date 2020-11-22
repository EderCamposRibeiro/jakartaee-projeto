package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	/* A anoteção "@PersistenceContext" cria uma instância do "EntityManager" e cria um contexto de persistência.*/
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<AgendamentoEmail> listar() {
		return entityManager.createQuery("SELECT ae FROM AgendamentoEmail ae",	
				AgendamentoEmail.class).getResultList();
	}
	
	/* O método "persist" persiste a entidade passada por parâmetro no banco de dados*/
	public void inserir(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
	public List<AgendamentoEmail> listarPorNaoAgendado(){
		return entityManager.
				createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = false",
						AgendamentoEmail.class).getResultList();
	}
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		entityManager.merge(agendamentoEmail);
	}
	
}
