package crud.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import crud.model.AlterarFilme;
import crud.model.ConsultarFilme;
import crud.model.DeletarFilme;
import crud.model.ListarFilme;
import crud.model.inserirFilme;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String service = request.getParameter("service");
			if(service.equals("inserirFilme")) {
				new inserirFilme().executar(request, response);
			} else if(service.equals("listarFilme")) {
				new ListarFilme().executar(request, response);
			} else if(service.equals("alterarFilme")) {
				new AlterarFilme().executar(request, response);
			} else if(service.equals("deletarFilme")) {
				new DeletarFilme().executar(request, response);
			} else if(service.equals("consultarFilme")) {
				new ConsultarFilme().executar(request, response);
			} else {
				JsonObject res = new JsonObject();
				res.addProperty("condicao", false);
				res.addProperty("mensagem", "Erro no Controller, verifique os parâmetros.");
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(res));
			}
			
		} catch (Exception e) {
			JsonObject res = new JsonObject();
			res.addProperty("condicao", false);
			res.addProperty("mensagem", "Erro no Controller, verifique os parâmetros.");
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(res));
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
