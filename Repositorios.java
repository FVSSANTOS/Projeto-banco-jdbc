package ProjetoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;


import db.DB;

public class Repositorios {
	private static Connection conn = null;
	public static void conexao(String nome,String cpf, Conta conta, Double saldo) throws ParseException {
	
	PreparedStatement st = null;
	try{
		conn = DB.getConnection();
		
		st = conn.prepareStatement(
				"INSERT INTO cliente"
				+"(Nome, CPF, Conta, Saldo)"
				+"VALUES"
				+"(?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
				
				st.setString(1, nome);
				st.setString(2, cpf);				
				st.setString(3, conta.getNumero());
				st.setDouble(4, saldo);
				
				st.executeUpdate();
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
}
	public static boolean procurar(String cpf,String nConta) {
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			conn = DB.getConnection();
	
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from cliente");
			
			while (rs.next()) {
				 
				if(rs.getString("CPF").equals(cpf) && rs.getString("Conta").equals(nConta)) {					
			      return true;
			    }
						
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean procurarCpf(String cpf) {
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from cliente");
			
			while(rs.next()) {
				if(rs.getString("CPF").equals(cpf)) {
					return true;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean procurarNumeroConta(String nConta) {
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from cliente");
			
			while(rs.next()) {
				if(rs.getString("Conta").equals(nConta)) {
					return true;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public static void fecharDB() {
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			conn = DB.getConnection();
	
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from cliente");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
	
	public static boolean deposito(double saldo, String cpf) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE cliente "
					+ "SET Saldo = Saldo + ? "
				    + "WHERE "
					+ "(CPF = ?)");
			
			st.setDouble(1, saldo);
		    st.setString(2, cpf);
		    
		    st.executeUpdate();
		    return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean saque(double valor, String nConta, String cpf) throws SQLException {
		
		Connection conn = null;
		PreparedStatement st = null;
		Statement st1 = null;
		ResultSet rs = null;
	    double saldoRemetente;
		try {
			conn = DB.getConnection();
			st1 = conn.createStatement();
			rs = st1.executeQuery("select * from cliente");
			
			while(rs.next()) {
				if(rs.getString("Conta").equals(nConta) && rs.getString("CPF").equals(cpf)) {
				saldoRemetente = rs.getDouble("Saldo");
				if(saldoRemetente >= valor) {
					st = conn.prepareStatement(
							"UPDATE cliente "
							+ "SET Saldo = Saldo - ?"
							+ "WHERE "
							+ "(Conta = ?)");
							
					st.setDouble(1,  valor);
					st.setString(2, nConta);
					
					st.executeUpdate();
				}else {
					return false;
				}
			}
			
		}
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	public static boolean transferencia(double valor, String nContaRemetente, String cpfD, String cpfR) throws SQLException {
		if(saque(valor, nContaRemetente, cpfR)) {
			return deposito(valor, cpfD);
		}
		return false;
	}
	}


