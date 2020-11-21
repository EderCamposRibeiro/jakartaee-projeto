package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	/* A anote��o "@PersistenceContext" cria uma inst�ncia do "EntityManager" e cria um contexto de persist�ncia.*/
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<AgendamentoEmail> listar() {
		return entityManager.createQuery("SELECT ae FROM AgendamentoEmail ae",	
				AgendamentoEmail.class).getResultList();
	}
	
	/* O m�todo "persist" persiste a entidade passada por par�metro no banco de dados*/
	public void inserir(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
}
