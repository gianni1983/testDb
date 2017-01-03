package it.testdb.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbService {
	
	private long minIns = 999999999;
	private long maxIns = 0;
	private double avgIns = 0;
	private int numIns = 0;
	private long minSel = 999999999;
	private long maxSel = 0;
	private double avgSel = 0;
	private int numSel = 0;
	private final int COMMIT_NUM = 15;
	
	
	public void executeInsert(Connection connection, PreparedStatement statement){
		try {
			
			long inizioMs = System.currentTimeMillis();
			statement.executeUpdate();
			long fineMs = System.currentTimeMillis();
		    long diffMs = fineMs - inizioMs;
		    
		    maxIns = diffMs > maxIns? diffMs:maxIns;
		    minIns = diffMs < minIns? diffMs:minIns;
		    avgIns = ((avgIns * numIns) + diffMs) / (numIns+1);
		    numIns++;
		    
		    if(numIns%COMMIT_NUM == 0){
				connection.commit();
			}
		    
		} catch (SQLException e) {
			System.out.println("ERRORE IN INSERIMENTO");
			e.printStackTrace();
		} finally {
	        if (statement != null) {
	        	try {
	        		statement.close();
				} catch (SQLException e) {
					System.out.println("ERRORE CHIUSURA STATEMENT");
					e.printStackTrace();
				}
	        }
	    }
	}
	
	public void executeSelect(Connection connection, PreparedStatement statement){
		try {
			
			long inizioMs = System.currentTimeMillis();
			statement.executeQuery();
			long fineMs = System.currentTimeMillis();
		    long diffMs = fineMs - inizioMs;
		    
		    maxSel = diffMs > maxSel? diffMs:maxSel;
		    minSel = diffMs < minSel? diffMs:minSel;
		    avgSel = ((avgSel * numSel) + diffMs) / (numSel+1);
		    numSel++;
		    
		    
		} catch (SQLException e) {
			System.out.println("ERRORE IN SELECT");
			e.printStackTrace();
		} finally {
	        if (statement != null) {
	        	try {
	        		statement.close();
				} catch (SQLException e) {
					System.out.println("ERRORE CHIUSURA STATEMENT");
					e.printStackTrace();
				}
	        }
	    }
	}
	
	
	public long getMinIns() {
		return minIns;
	}
	public long getMaxIns() {
		return maxIns;
	}
	public double getAvgIns() {
		return avgIns;
	}
	public int getNumIns() {
		return numIns;
	}
	public long getMinSel() {
		return minSel;
	}
	public long getMaxSel() {
		return maxSel;
	}
	public double getAvgSel() {
		return avgSel;
	}
	public int getNumSel() {
		return numSel;
	}
	public int getCOMMIT_NUM() {
		return COMMIT_NUM;
	}
	
	
	

}
