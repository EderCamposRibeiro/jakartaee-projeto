package br.com.alura.servico;

import java.util.List;

import javax.ejb.Stateless;

/* O EJB faz com que haja a inversão de controle, passando a responsabilidade
 * de instanciar objetos e fornecimento de serviços para o servidor de aplica-
 * ção. Para criar um EJB basta anotar uma classe com "@Stateless"*/

@Stateless
public class AgendamentoEmailServico {
	
	public List<String> listar() {
		return List.of("joao@alura.com.br");
	}

}
