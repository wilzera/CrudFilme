package crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
private Connection conexao;
	public Conexao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/bdfilme";
			conexao = DriverManager.getConnection(url, "root", "123456");
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void desconecta() {
		try {
			conexao.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void setConexao(Connection conexao) { this.conexao = conexao; }
	
	public Connection getConexao() { return conexao; }

}
