package br.com.alura.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entidade.AgendamentoEmail;

/* O EJB faz com que haja a invers�o de controle, passando a responsabilidade
 * de instanciar objetos e fornecimento de servi�os para o servidor de aplica-
 * ��o. Para criar um EJB basta anotar uma classe com "@Stateless"*/

@Stateless
public class AgendamentoEmailServico {
	
	@Inject
	private AgendamentoEmailDAO dao;
	
	public List<AgendamentoEmail> listar() {
		
		return dao.listar();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(false);
		dao.inserir(agendamentoEmail);
	}

}
