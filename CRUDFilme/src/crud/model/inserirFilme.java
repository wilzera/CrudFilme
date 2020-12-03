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

@WebServlet("/inserirFilme")
public class inserirFilme extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public inserirFilme() {
        super();
    }

	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject res = new JsonObject();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		FilmeDao fDao = new FilmeDao();
		Filme f = new Filme();
		
		try {
			
			f.setTitulo(request.getParameter("titulo"));
			f.setDiretor(request.getParameter("diretor"));
			f.setEstudio(request.getParameter("estudio"));
			f.setLancamento(Integer.parseInt(request.getParameter("lancamento")));
			f.setDuracao(Integer.parseInt(request.getParameter("duracao")));
			
			res = fDao.inserir(f);
			
		} catch (Exception e) {
			res.addProperty("condicao", false);
			res.addProperty("mensagem", "Não foi possível salvar o Filme.");
			e.printStackTrace();
		}
		
		out.print(gson.toJson(res));
	}

}
