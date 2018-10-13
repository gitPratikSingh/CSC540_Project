package db;

import java.io.*;
import java.sql.*;

public class DBBuilder {
	
	private static String[] tables = new String[]{
		"car",
		"order",
		"customer",
		"service_center",
		"inventory",
		"appointment",
		"invoice",
		"employee",
		"payroll",
		"hourly_payroll",
		"monthly_payroll",
		"timeslot",
		"service",
		"maintenance_service",
		"repair_service", 
		"distributor"};
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            connection = DriverManager.getConnection(
	                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "hr");
	        } catch (ClassNotFoundException | SQLException e) {
	            System.out.println("Connection failed");
	            e.printStackTrace();
	            return null;
	        }
		}
        return connection;
	}
	
	public static void close() {
	    if(connection != null) {
	        try { 
	        	connection.close();
	        	connection = null;
	        } catch(Throwable t) {
	        	t.printStackTrace();
	        }
	    }
	}
	
	public static void close(Statement st) {
	    if(st != null) {
	        try { st.close(); } catch(Throwable whatever) {}
	    }
	}
	
	public static void close(ResultSet rs) {
	    if(rs != null) {
	        try { rs.close(); } catch(Throwable whatever) {}
	    }
	}
	
	public static void createTables() {
		getConnection();
		Statement stmt = null;
        ResultSet rs = null;
        try {	
			stmt = connection.createStatement();
			String sql = readSQL("sql/createTables.sql");
	        System.out.println(sql);
			stmt.executeUpdate(sql);
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
        close(rs);
        close(stmt);
        close();
	}
	
	public static void dropTables() {
		Statement stmt = null;
        try {	
			stmt = connection.createStatement();
			String sql = "DROP TABLE ";
			for (String t: tables) {
				stmt.executeUpdate(sql+t);
				System.out.println(sql+t);
			}
		} 
    	catch(Throwable e) {
	        e.printStackTrace();
	    }
        close(stmt);
	}
	
	public static void populateTables() {
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			
			stmt.executeUpdate(". add code here .");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(stmt);
	}
	
	private static String readSQL(String path) {
		String sql = "";
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sql += line;
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return sql;
	}
	public static void main(String[] args) {
		//Test connection
		connection = getConnection();
        if (connection != null) {
            System.out.println("Connected...");
            
            dropTables();
            createTables();
            populateTables();
            
        } else {
            System.out.println("Connection failed!");
        }
        
        close();
	}
	
}
