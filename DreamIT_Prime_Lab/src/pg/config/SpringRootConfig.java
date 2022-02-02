/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration      
//@ComponentScan(basePackages = {"pg"})
public class SpringRootConfig {
	//TODO Serice,Dao,datasource ,Email Sendar or some other busniess layer beans.


	
	String database_url   = "jdbc:sqlserver://localhost;databaseName=DreamIT_Prime_ERP";
	//String database_url   = "jdbc:sqlserver://192.168.16.160:1433;databaseName=DreamIT_Prime_ERP";
		
	String username       = "sa";
	//String password       = "Cursor777";
	String password       = "Admin@2021";
	Connection connection = null;

	@Bean
	public BasicDataSource getDataSource(){
		BasicDataSource bds=new BasicDataSource();
		bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		bds.setUrl("jdbc:sqlserver://localhost;databaseName =DreamIT_Prime_ERP");
		//bds.setUrl("jdbc:sqlserver://192.168.16.160:1433;databaseName =DreamIT_Prime_ERP");


		bds.setUsername("sa");
		bds.setPassword("Admin@2021");
		//bds.setPassword("Cursor777");
		bds.setMaxTotal(2);
		bds.setInitialSize(1);
		bds.setTestOnBorrow(true);
		bds.setValidationQuery("SELECT 1");
		bds.setDefaultAutoCommit(true);
		return bds;
	}
	

	public SpringRootConfig() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: Unable to load SQLServer JDBC Driver");
			e.printStackTrace();   
			return;
		}
		try {
			connection = DriverManager.getConnection(database_url, username, password);
		} catch (SQLException e) {
			System.out.println("ERROR:  Unable to establish a connection with the database!");
			e.printStackTrace();
			return;
		}
	}



	public Connection getConnection() {
		try { 	   
			connection = DriverManager.getConnection(database_url, username, password);  	   
		} catch (SQLException e) {  	   
			System.out.println("ERROR:  Unable to establish a connection with the database!");	   
			e.printStackTrace();
			return null;
		}        
		return connection;   
	}


	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
