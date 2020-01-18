package de.unidue.inf.is.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ibm.db2.jcc.DB2Driver;

import de.unidue.inf.is.domain.Project;



public final class DBUtil {

    private DBUtil() {
    }


    static {
        com.ibm.db2.jcc.DB2Driver driver = new DB2Driver();
        try {
            DriverManager.registerDriver(driver);
        }
        catch (SQLException e) {
            throw new Error("Laden des Datenbanktreiber nicht möglich");
        }
    }

    
    public static Connection getConnection() throws SQLException {
    	Properties properties = new Properties();
        
        InputStream input = null;
    	try {
    		input = new FileInputStream("settings.properties");

    		// Zugangsdaten aus der Properties-Datei lesen
    		properties.load(input);
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	
    	String database = properties.getProperty("database");
        final String url = "jdbc:db2:" + database;
        return DriverManager.getConnection(url);
    }


    // Diese Methode benutzen, um sich von außerhalb der Uni mit der DB zu verbinden
    public static Connection getExternalConnection() throws SQLException {
        Properties properties = new Properties();
        
        InputStream input = null;
    	try {
    		input = new FileInputStream("settings.properties");

    		// Zugangsdaten aus der Properties-Datei lesen
    		properties.load(input);
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    	
		String user = properties.getProperty("gruppenname");
		String pass = properties.getProperty("passwort");
		String rechnername = properties.getProperty("rechnername");
		String database = properties.getProperty("database");
		
		String gruppennummer = user.split("(?<=\\D)(?=\\d)")[1];

        final String url = "jdbc:db2://"+rechnername+".is.inf.uni-due.de:50"+gruppennummer+"/" + database + ":currentSchema="+user+";";
        Connection connection = DriverManager.getConnection(url, user,pass);
        return connection;
    }
    
    public static List<Project> getProjects()
    {
    	List<Project> list = new ArrayList<>();
    	Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    	Statement stmt = null;
    	ResultSet rs = null;
    	 
		stmt = connection.createStatement();
		rs = stmt.executeQuery("SELECT t1.TITEL, t1.BESCHREIBUNG, t1.STATUS, t1.FINANZIERUNGSLIMIT, t1.ERSTELLER, t1.KATEGORIE, COALESCE(t2.SPENDENBETRAG, 0) SPENDENBETRAG\n" + 
				"FROM dbp061.PROJEKT t1\n" + 
				"LEFT JOIN \n" + 
				"	(SELECT PROJEKT, SUM(SPENDENBETRAG) as SPENDENBETRAG FROM dbp061.SPENDEN GROUP BY PROJEKT) t2\n" + 
				"ON t1.KENNUNG = t2.PROJEKT");
		
    	 while (rs.next()) {
    	        list.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getFloat(7)));
    	      }
    	 rs.close();
    	 stmt.close();
    	 connection.commit();
    	 connection.close();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }

    public static boolean checkDatabaseExistsExternal() {
        // Nur für Demozwecke!
        boolean exists = false;

        try (Connection connection = getExternalConnection()) {
            exists = true;
        }
        catch (SQLException e) {
            exists = false;
            e.printStackTrace();
        }

        return exists;
    }


    public static boolean checkDatabaseExists() {
        // Nur für Demozwecke!
        boolean exists = false;

        try (Connection connection = getConnection()) {
            exists = true;
        }
        catch (SQLException e) {
            exists = false;
            e.printStackTrace();
        }

        return exists;
    }

}