package SqlConect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConeccaoBancodeDados {
	
	    public void conecPostgre() {
	        String driver = "jdbc:postgresql://192.168.0.105:5432/database";
	        String user = "mycontroller";
	        String senha = "sIIsthUlsO";
	        String url = "192.168.0.105";
	        try {
	            Class.forName(driver);
	            Connection con = null;
	            con = (Connection) DriverManager.getConnection(url, user, senha);
	            JOptionPane.showMessageDialog(null, "Conexão realizada com Sucesso!");
	            Statement sq_stmt = (Statement) con.createStatement();
	            ResultSet rs = sq_stmt.executeQuery("SELECT * FROM dados");
	            System.out.println(rs.toString());
	        } catch (ClassNotFoundException ex) {
	            System.err.print(ex.getMessage());
	        } catch (SQLException e) {
	            System.err.print(e.getMessage());
	        }
	    }
	
}
