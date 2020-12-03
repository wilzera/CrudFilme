package crud.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import crud.db.Filme;
import crud.dto.FilmeDao;

/**
 * Servlet implementation class DeletarFilme
 */
@WebServlet("/DeletarFilme")
public class DeletarFilme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletarFilme() {
    	super();
    }

	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject res = new JsonObject();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		FilmeDao fDao = new FilmeDao();
		Filme f = new Filme();
		
		try {
			
			f.setId(Integer.parseInt(request.getParameter("id")));
			res = fDao.excluir(f);
			
		} catch (Exception e) {
			res.addProperty("condicao", false);
			res.addProperty("mensagem", "Não foi possível excluir o Filme.");
			e.printStackTrace();
		}
		
		out.print(gson.toJson(res));
	}

}
