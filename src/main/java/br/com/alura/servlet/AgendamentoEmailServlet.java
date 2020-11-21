package br.com.alura.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@WebServlet("emails")
public class AgendamentoEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * Podemos fazer o uso da injeção de dependências, usando a anotação "@Inject",
	 * para obter uma instância de um EJB. Dessa maneira não precisamos nos
	 * preocupar em instanciar um objeto "na mão", ou seja, utilizando a palavra
	 * reservada "new" e o resultado disso é que temos um menor acoplamento entre as
	 * classes. Além da anotação "@Inject", nós temos a anotação "@EJB", que entrega
	 * o mesmo resultado citado acima. A diferença é que a anotação "@EJB" só
	 * funcionará se você estiver trabalhando com um EJB, caso contrário ocorrerá
	 * uma exceção. Já a anotação "@Inject" funcionará para EJB e, também, para
	 * outros recursos habilitados pa- ra injeção de dependência
	 */

	@Inject
	private AgendamentoEmailServico servico;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		servico.listar().forEach(resultado -> pw.print("Os e-mails disponíveis são: " + resultado.getEmail()));
	}
	
	@Override
	/* Email, assunto, mensagem*/
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		String[] email = reader.readLine().split(",");
		AgendamentoEmail agendamentoEmail = new AgendamentoEmail();
		agendamentoEmail.setEmail(email[0]);
		agendamentoEmail.setAssunto(email[1]);
		agendamentoEmail.setMensagem(email[2]);
		servico.inserir(agendamentoEmail);
	}

}
