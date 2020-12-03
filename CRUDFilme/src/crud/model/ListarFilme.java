package crud.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crud.db.Filme;
import crud.dto.FilmeDao;

@WebServlet("/ListarFilme")
public class ListarFilme extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListarFilme() {
    	super();
    }

	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Filme> res = new LinkedList<Filme>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		FilmeDao fDao = new FilmeDao();

		try {	
			res = fDao.listar();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.print(gson.toJson(res));
	}

}
