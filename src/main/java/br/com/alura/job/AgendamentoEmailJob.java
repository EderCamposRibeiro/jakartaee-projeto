package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
//import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

/* Para usar o Time Service, precisamos ter uma classe anotada como "EJB", para haver
 * a inver�o de controle e o servidor ser gerenciado pelo servidor de aplica��o.*/
//@Stateless

@Singleton
public class AgendamentoEmailJob {
	
	/* Para evitar a poss�vel concorr�ncia entra as inst�ncias, passamos o nosso processador para o padr�o "Singleton"*/
//	private static AgendamentoEmailJob instance;
//	
//	private AgendamentoEmailJob() {}
//	
//	public synchronized static AgendamentoEmailJob getInstance() {
//		if (instance == null) {
//			instance = new AgendamentoEmailJob();
//		} return instance;
//	}
	
	/* O problema de realizar o Singleton da forma acima � que n�o poderiamos usar a anota��o "@Schedule", que � do EJB.*/
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;

	/* Ao anotar uma classe que � um "EJB Timer", o contexto "Jakarta EE" controla o processamento para que n�o haja
	 * dois processamento em paralelo*/
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public synchronized void enviarEmail() {
		List<AgendamentoEmail> listarPorNaoAgendado 
			= agendamentoEmailServico.listarPorNaoAgendado();
		listarPorNaoAgendado.forEach(emailNaoAgendado -> {
			agendamentoEmailServico.enviar(emailNaoAgendado);
			agendamentoEmailServico.alterar(emailNaoAgendado);
		});
	}
}
