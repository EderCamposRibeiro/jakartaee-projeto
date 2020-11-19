package br.com.alura.servico;

import java.util.List;

import javax.ejb.Stateless;

/* O EJB faz com que haja a invers�o de controle, passando a responsabilidade
 * de instanciar objetos e fornecimento de servi�os para o servidor de aplica-
 * ��o. Para criar um EJB basta anotar uma classe com "@Stateless"*/

@Stateless
public class AgendamentoEmailServico {
	
	public List<String> listar() {
		return List.of("joao@alura.com.br");
	}

}
