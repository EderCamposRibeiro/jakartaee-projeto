package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
//import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

/* Para usar o Time Service, precisamos ter uma classe anotada como "EJB", para haver
 * a inverão de controle e o servidor ser gerenciado pelo servidor de aplicação.*/
//@Stateless

@Singleton
public class AgendamentoEmailJob {
	
	/* Para evitar a possível concorrência entra as instâncias, passamos o nosso processador para o padrão "Singleton"*/
//	private static AgendamentoEmailJob instance;
//	
//	private AgendamentoEmailJob() {}
//	
//	public synchronized static AgendamentoEmailJob getInstance() {
//		if (instance == null) {
//			instance = new AgendamentoEmailJob();
//		} return instance;
//	}
	
	/* O problema de realizar o Singleton da forma acima é que não poderiamos usar a anotação "@Schedule", que é do EJB.*/
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;

	/* Ao anotar uma classe que é um "EJB Timer", o contexto "Jakarta EE" controla o processamento para que não haja
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
