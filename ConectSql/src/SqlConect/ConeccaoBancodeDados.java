package SqlConect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConeccaoBancodeDados {

	public void conecPostgre(String[][] strings, int quantDados) {
		try {
			String fonte = "jdbc:postgresql://192.168.0.105:5432/mycontroller";
			String usuario = "postgres";
			String senha = "sIIsthUlsO";
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(fonte, usuario, senha);
			Statement statement = con.createStatement();

			// insert the data
			// statement.executeUpdate("INSERT INTO dados " + "VALUES
			// (2455,'nome','2012-06-18','10:34:09','{ \"name\":\"John\" }')");
			for (int i = 0; i < quantDados; i++)
				statement.executeUpdate("INSERT INTO dados (id_estacao,hash,nome_estacao,data,hora,sensores,created_at,updated_at)" + "VALUES("
						+ strings[i][2] + ",'"+ strings[i][0]+"','"+ strings[i][1] + "','" + strings[i][3] + "','" + strings[i][4] + "','"
						+ strings[i][5] + "',CURRENT_DATE,CURRENT_DATE)");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro em ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro em SQLException: " + e.getMessage());
		} catch (NoClassDefFoundError e) {
			JOptionPane.showMessageDialog(null, "Erro em NoClassDefFoundError: " + e.getMessage());
		}
	}

}
