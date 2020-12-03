package crud.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import com.google.gson.JsonObject;

import crud.db.Conexao;
import crud.db.Filme;

public class FilmeDao {
	
	public JsonObject inserir(Filme a) {
		JsonObject res = new JsonObject();
		res.addProperty("condicao", false);
		Conexao con = new Conexao();
		try {
			String sql = "INSERT INTO filme(titulo, diretor, estudio, lancamento, duracao) VALUES (?,?,?,?,?)";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, a.getTitulo());
			prep.setString(2, a.getDiretor());
			prep.setString(3, a.getEstudio());
			prep.setInt(4, a.getLancamento());
			prep.setInt(5, a.getDuracao());
			prep.execute();
			
			res.addProperty("condicao", true);
			res.addProperty("mensagem", "Inserido com sucesso+");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.addProperty("mensagem", "Não foi possível cadastrar o produto." + e.getLocalizedMessage());
		}
		con.desconecta();
		return res;
	}
	
	public JsonObject alterar(Filme a) {
		JsonObject res = new JsonObject();
		res.addProperty("condicao", false);
		Conexao con = new Conexao();
		try {
			String sql = "UPDATE filme SET titulo=?, diretor=?, estudio=?, lancamento=?, duracao=? WHERE id=?";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, a.getTitulo());
			prep.setString(2, a.getDiretor());
			prep.setString(3, a.getEstudio());
			prep.setInt(4, a.getLancamento());
			prep.setInt(5, a.getDuracao());
			prep.setInt(6, a.getId());
			prep.execute();
			
			res.addProperty("condicao", true);
			res.addProperty("mensagem", "Alterado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.addProperty("mensagem", "Não foi possível alterar o produto.");
		}
		con.desconecta();
		return res;
	}
	
	public JsonObject excluir(Filme a) {
		JsonObject res = new JsonObject();
		res.addProperty("condicao", false);
		Conexao con = new Conexao();
		try {
			String sql = "DELETE FROM filme WHERE id=?";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setInt(1, a.getId());
			prep.execute();
			
			res.addProperty("condicao", true);
			res.addProperty("mensagem", "Deletado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.addProperty("mensagem", "Não foi possível deletar o registro.");
		}
		con.desconecta();
		return res;
	}
	
	public LinkedList<Filme> listar() {
		Conexao con = new Conexao();
		LinkedList<Filme> listaFilme = new LinkedList<Filme>();
		try {
			String sql = "SELECT id, titulo, diretor, estudio, lancamento, duracao FROM filme";
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);		
			while (res.next()) {
				Filme a = new Filme();
				a.setId(res.getInt("id"));
				a.setTitulo(res.getString("titulo"));
				a.setDiretor(res.getString("diretor"));
				a.setEstudio(res.getString("estudio"));
				a.setLancamento(res.getInt("lancamento"));
				a.setDuracao(res.getInt("duracao"));
				listaFilme.add(a);
			}
			res.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return listaFilme;
	}
	
	public Filme consultar(int id) {
		Conexao con = new Conexao();
		Filme a = new Filme();
		try {
			String sql = "SELECT id, titulo, diretor, estudio, lancamento, duracao FROM filme WHERE id = " + id;
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);		
			if (res.next()) {
				a.setId(res.getInt("id"));
				a.setTitulo(res.getString("titulo"));
				a.setDiretor(res.getString("diretor"));
				a.setEstudio(res.getString("estudio"));
				a.setLancamento(res.getInt("lancamento"));
				a.setDuracao(res.getInt("duracao"));
			}
			res.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return a;
	}
}
