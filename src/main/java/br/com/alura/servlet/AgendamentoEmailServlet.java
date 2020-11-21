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
	 * Podemos fazer o uso da inje��o de depend�ncias, usando a anota��o "@Inject",
	 * para obter uma inst�ncia de um EJB. Dessa maneira n�o precisamos nos
	 * preocupar em instanciar um objeto "na m�o", ou seja, utilizando a palavra
	 * reservada "new" e o resultado disso � que temos um menor acoplamento entre as
	 * classes. Al�m da anota��o "@Inject", n�s temos a anota��o "@EJB", que entrega
	 * o mesmo resultado citado acima. A diferen�a � que a anota��o "@EJB" s�
	 * funcionar� se voc� estiver trabalhando com um EJB, caso contr�rio ocorrer�
	 * uma exce��o. J� a anota��o "@Inject" funcionar� para EJB e, tamb�m, para
	 * outros recursos habilitados pa- ra inje��o de depend�ncia
	 */

	@Inject
	private AgendamentoEmailServico servico;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		servico.listar().forEach(resultado -> pw.print("Os e-mails dispon�veis s�o: " + resultado.getEmail()));
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
