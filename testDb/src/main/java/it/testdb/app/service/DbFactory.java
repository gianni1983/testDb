package it.testdb.app.service;

import it.testdb.app.DbService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class DbFactory {
	
	private Connection connection;
	private DbService service;
	
	public DbFactory() {
		super();
		service = new DbService();
		initConnection();
	}
	
	public void closeConnection(){
		try {
			connection.close();
			System.out.println("CONNESSIONE CHIUSA");
		} catch (SQLException e) {
			System.out.println("ERRORE CHIUSURA CONNESSIONE");
			e.printStackTrace();
		}
	}

	private void initConnection(){
		
		String driver = null;
		String url = null;
		String user = null;
		String password = null;
		InputStream inputStream = null;
		Properties prop = new Properties();
		
		try {
			
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' non trovato nel classpath");
			}
  
			// recupero valori dal file di properties
			driver = prop.getProperty("driver");
			
			Class.forName(driver);
			
			
		} catch (ClassNotFoundException e) {
	
			System.out.println("Connessione fallita");
			e.printStackTrace();
	
		}
		catch (Exception e) {
			System.out.println("Eccezione: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Eccezione: " + e);
			}
		}
		
		try {
			
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
			connection = DriverManager.getConnection(url, user,password);
			
			connection.setAutoCommit(false);
			System.out.println("CONNESSIONE RIUSCITA");
			
		} catch (SQLException e) {
	
			System.out.println("Connessione fallita");
			e.printStackTrace();
	
		}
	}
	
	public void insert(){
		
		PreparedStatement insUtente = null;
		PreparedStatement insArticolo = null;
		PreparedStatement insOrdine = null;
		Random random = new Random();
		
	    String insUtenteStr = "INSERT INTO utente( id, cod_utente, desc_utente) VALUES (?, ?, ?)";
	    String insArticoloStr = "INSERT INTO articolo(id, cod_articolo, desc_articolo)VALUES (?, ?, ?)";
	    String insOrdineStr = "INSERT INTO ordine(id, cod_ordine, desc_ordine, id_utente, id_articolo, quantita)VALUES (?, ?, ?, ?, ?, ?)";

	    try {
			for(int i=0; i<500; i++){
		    	insUtente = connection.prepareStatement(insUtenteStr);
				
				insUtente.setInt(1, i);
				insUtente.setString(2, "COD UTENTE"+i);
				insUtente.setString(3, "DESC UTENTE"+i);
				
				service.executeInsert(connection, insUtente);
				
				
				insArticolo = connection.prepareStatement(insArticoloStr);
				
				insArticolo.setInt(1, i);
				insArticolo.setString(2, "COD ARTICOLO"+i);
				insArticolo.setString(3, "DESC ARTICOLO"+i);
				
				service.executeInsert(connection, insArticolo);
				
				
				insOrdine = connection.prepareStatement(insOrdineStr);
				
				insOrdine.setInt(1, i);
				insOrdine.setString(2, "COD ORDINE"+i);
				insOrdine.setString(3, "DESC ORDINE"+i);
				insOrdine.setInt(4, random.nextInt(i + 1));
				insOrdine.setInt(5, random.nextInt(i + 1));
				insOrdine.setInt(6, random.nextInt(10 - 1 + 1) + 1);
				
				service.executeInsert(connection, insOrdine);
				
			}
			connection.commit();
		} catch (SQLException e) {
			System.out.println("ERRORE IN INSERIMENTO");
			e.printStackTrace();
		} finally {
	        if (insUtente != null) {
	        	try {
					insUtente.close();
				} catch (SQLException e) {
					System.out.println("ERRORE CHIUSURA STATEMENT");
					e.printStackTrace();
				}
	        }
	    }
	}
	
	public void select(){
		
		PreparedStatement selUtente = null;
		PreparedStatement selArticolo = null;
		PreparedStatement selOrdine = null;
		Random random = new Random();
		
	    String selUtenteStr = "SELECT id, cod_utente, desc_utente FROM utente WHERE id = ? ";
	    String selArticoloStr = "SELECT id, cod_articolo, desc_articolo FROM articolo WHERE id = ?";
	    String selOrdineStr = "SELECT id, cod_ordine, desc_ordine, id_utente, id_articolo, quantita FROM ordine WHERE id = ?";

	    try {
			for(int i=0; i<500; i++){
		    	selUtente = connection.prepareStatement(selUtenteStr);
				
				selUtente.setInt(1, random.nextInt(500));
				
				service.executeSelect(connection, selUtente);
				
				
				selArticolo = connection.prepareStatement(selArticoloStr);
				
				selArticolo.setInt(1, random.nextInt(500));
				
				service.executeSelect(connection, selArticolo);
				
				
				selOrdine = connection.prepareStatement(selOrdineStr);
				
				selOrdine.setInt(1, random.nextInt(500));
				
				service.executeSelect(connection, selOrdine);
				
			}
		} catch (SQLException e) {
			System.out.println("ERRORE IN SELECT");
			e.printStackTrace();
		} finally {
	        if (selUtente != null) {
	        	try {
					selUtente.close();
				} catch (SQLException e) {
					System.out.println("ERRORE CHIUSURA STATEMENT");
					e.printStackTrace();
				}
	        }
	    }
	}
	
	public long getMinIns() {
		return service.getMinIns();
	}

	public long getMaxIns() {
		return service.getMaxIns();
	}

	public double getAvgIns() {
		return service.getAvgIns();
	}

	public int getNumIns() {
		return service.getNumIns();
	}

	public long getMinSel() {
		return service.getMinSel();
	}

	public long getMaxSel() {
		return service.getMaxSel();
	}

	public double getAvgSel() {
		return service.getAvgSel();
	}

	public int getNumSel() {
		return service.getNumSel();
	}
	
	
}
