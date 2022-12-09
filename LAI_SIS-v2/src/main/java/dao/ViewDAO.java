package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ViewDAO {
	DataSource ds;
	public ViewDAO() throws ClassNotFoundException{
		// TODO Auto-generated constructor stub
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public String retrieve(String namePrefix) throws SQLException{
		String query = "select * from item where name like '%" + namePrefix +"%'";
		String rv = "";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		String des = r.getString("DESCRIPTION");
		String quantity = r.getString("QUANTITY");
		int price = r.getInt("PRICE");
		rv = "Price: " + price + "\n" + "Quantity: " + quantity + "\n" + "Description: " + des;
		
		r.close();
		p.close();
		con.close();
		return rv;
	}

}
