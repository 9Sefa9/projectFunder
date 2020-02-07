package de.unidue.inf.is.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.ParseConversionEvent;

import com.ibm.db2.jcc.DB2Driver;

import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.User;



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
		rs = stmt.executeQuery("SELECT t1.TITEL, t1.BESCHREIBUNG, t1.STATUS, t1.FINANZIERUNGSLIMIT, t1.ERSTELLER, t1.KATEGORIE, COALESCE(t2.SPENDENBETRAG, 0) SPENDENBETRAG , t1.KENNUNG\n" + 
				"FROM dbp061.PROJEKT t1\n" + 
				"LEFT JOIN \n" + 
				"	(SELECT PROJEKT, SUM(SPENDENBETRAG) as SPENDENBETRAG FROM dbp061.SPENDEN GROUP BY PROJEKT) t2\n" + 
				"ON t1.KENNUNG = t2.PROJEKT WHERE STATUS = 'offen'");
		
    	 while (rs.next()) {
    	        list.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8)));
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

    public static List<Project> getClosedProjects()
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
		rs = stmt.executeQuery("SELECT t1.TITEL, t1.BESCHREIBUNG, t1.STATUS, t1.FINANZIERUNGSLIMIT, t1.ERSTELLER, t1.KATEGORIE, COALESCE(t2.SPENDENBETRAG, 0) SPENDENBETRAG , t1.KENNUNG\n" + 
				"FROM dbp061.PROJEKT t1\n" + 
				"LEFT JOIN \n" + 
				"	(SELECT PROJEKT, SUM(SPENDENBETRAG) as SPENDENBETRAG FROM dbp061.SPENDEN GROUP BY PROJEKT) t2\n" + 
				"ON t1.KENNUNG = t2.PROJEKT WHERE STATUS = 'geschlossen'");
		
    	 while (rs.next()) {
    	        list.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getFloat(7),rs.getInt(8)));
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


	public static void insertProject(String ersteller, String title, String description, String financeLimit) throws SQLException {
		String inserNewPRojectSql = "INSERT INTO dbp061.PROJEKT (TITEL, BESCHREIBUNG, ERSTELLER, STATUS, Finanzierungslimit, Kategorie ) VALUES (?,?,?,?,?,?)";
		Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	PreparedStatement ps = connection.prepareStatement(inserNewPRojectSql);
    	ps.setString(1, title);
    	ps.setString(2, description);
    	ps.setString(3, ersteller);
    	ps.setString(4, "offen");
    	ps.setInt(5,Integer.parseInt(financeLimit) );
    	ps.setInt(6, 1);
    	ps.executeUpdate();
    	connection.commit();
    	connection.close();
	}


	public static User getUser(String userEmail) throws SQLException {
		User us = null;
		String userSQL = "SELECT NAME FROM dbp061.BENUTZER WHERE EMAIL = ?";
		String anzahlSQL = "SELECT COUNT(*) FROM dbp061.PROJEKT WHERE ERSTELLER=?";
		String supportSQL = "SELECT COUNT(*) FROM dbp061.SPENDEN WHERE SPENDER=?";
		Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ResultSet rs = null;
    	ResultSet rsb = null;
    	ResultSet rsc = null;
    	PreparedStatement ps = connection.prepareStatement(userSQL);
    	ps.setString(1, userEmail);
    	rs = ps.executeQuery();
    	String name = null;
    	while (rs.next()) {
	        name = rs.getString(1);
	      }
    	PreparedStatement psb = connection.prepareStatement(anzahlSQL);
    	psb.setString(1, userEmail);
    	rsb = psb.executeQuery();
    	int anzahl = 0;
    	while (rsb.next()) {
	        anzahl = rsb.getInt(1);
	      }
    	PreparedStatement psc = connection.prepareStatement(supportSQL);
    	psc.setString(1, userEmail);
    	rsc = psc.executeQuery();
    	int support = 0;
    	while (rsc.next()) {
	        support = rsc.getInt(1);
	      }
    	connection.commit();
    	connection.close();
    	return new User(name,userEmail,anzahl,support);
	}


	public static List<Project> getProjects(String userEmail) throws SQLException {
		List<Project> list = new ArrayList<>();
		String project ="SELECT t1.TITEL, t1.BESCHREIBUNG, t1.STATUS, t1.FINANZIERUNGSLIMIT, t1.ERSTELLER, t1.KATEGORIE, COALESCE(t2.SPENDENBETRAG, 0) SPENDENBETRAG, t1.KENNUNG " + 
		"FROM dbp061.PROJEKT t1\n" + 
		"LEFT JOIN \n" + 
		"	(SELECT PROJEKT, SUM(SPENDENBETRAG) as SPENDENBETRAG FROM dbp061.SPENDEN GROUP BY PROJEKT) t2\n" + 
		"ON t1.KENNUNG = t2.PROJEKT WHERE ERSTELLER = ?";
		
		Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ResultSet rs = null;
    	PreparedStatement ps = connection.prepareStatement(project);
    	ps.setString(1, userEmail);
    	rs = ps.executeQuery();
    	while (rs.next()) {
	        list.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8)));
	      }
		return list;
	}
	
	public static List<Project> getSupported(String userEmail) throws SQLException {
		List<Project> list = new ArrayList<>();
		String project ="SELECT * FROM (SELECT t1.TITEL, t1.BESCHREIBUNG, t1.STATUS, t1.FINANZIERUNGSLIMIT, t1.ERSTELLER, t1.KATEGORIE, COALESCE(t2.SPENDENBETRAG, 0) SPENDENBETRAG, t1.KENNUNG \n" + 
				"FROM dbp061.PROJEKT t1\n" + 
				"LEFT JOIN \n" + 
				"	(SELECT PROJEKT, SUM(SPENDENBETRAG) as SPENDENBETRAG FROM dbp061.SPENDEN GROUP BY PROJEKT) t2\n" + 
				"ON t1.KENNUNG = t2.PROJEKT) WHERE KENNUNG = (SELECT PROJEKT as KENNUNG FROM dbp061.SPENDEN WHERE SPENDER = ?)";
		
		Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ResultSet rs = null;
    	PreparedStatement ps = connection.prepareStatement(project);
    	ps.setString(1, userEmail);
    	rs = ps.executeQuery();
    	while (rs.next()) {
	        list.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8)));
	      }
		return list;
	}
	public static Project getProjectById(int id) throws SQLException {
		Project p = null;
		String project ="SELECT t1.TITEL, t1.BESCHREIBUNG, t1.STATUS, t1.FINANZIERUNGSLIMIT, t1.ERSTELLER, t1.KATEGORIE, COALESCE(t2.SPENDENBETRAG, 0) SPENDENBETRAG, t1.KENNUNG\n" + 
				"FROM dbp061.PROJEKT t1\n" + 
				"LEFT JOIN \n" + 
				"	(SELECT PROJEKT, SUM(SPENDENBETRAG) as SPENDENBETRAG FROM dbp061.SPENDEN GROUP BY PROJEKT) t2\n" + 
				"ON t1.KENNUNG = t2.PROJEKT WHERE KENNUNG = ?";
		Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ResultSet rs = null;
		PreparedStatement ps = connection.prepareStatement(project);
    	ps.setInt(1, id);
    	rs = ps.executeQuery();
    	while (rs.next()) {
    	 p = new Project(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8));
    		
    	}
    	return p;
    }


	public static void newSpende(int spende, int kennung) throws SQLException {
		String inserNewPRojectSql = "INSERT INTO dbp061.SPENDEN (SPENDER, PROJEKT, SPENDENBETRAG, SICHTBARKEIT) VALUES (?,?,?,?)";
		Connection connection = null;
    	try {
    		connection = getExternalConnection();
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	PreparedStatement ps = connection.prepareStatement(inserNewPRojectSql);
    	ps.setString(1, "dummy@dummy.com");
    	ps.setInt(2, kennung);
    	ps.setBigDecimal(3, new BigDecimal(spende));
    	ps.setString(4, "oeffentlich");
    	ps.executeUpdate();
    	connection.commit();
    	connection.close();
		
	}

}