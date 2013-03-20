package org.aquamethods.fashbook.experiments;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	try{
		//String url = "jdbc:mysql://fashbookdb.czyv40ke43a9.us-east-1.rds.amazonaws.com:3306/fashbookdb";
		
		String url = "jdbc:mysql://localhost/fashbookdb";
		String userName = "root";
		String password = "password77";
		String driver = "com.mysql.jdbc.Driver";
		Connection connection = DriverManager.getConnection(url, userName, password);
		
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM person" ) ;
		
		 while( rs.next() ){
	         System.out.println( rs.getString(1) ) ;
		 }
	}catch(Exception ex){
		ex.printStackTrace();
	}
	}

}
