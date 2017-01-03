package it.testdb.app;

import java.sql.Connection;

import it.testdb.app.service.DbFactory;



public class DbMain {
	
	
	
    public static void main( String[] args ){
        
        DbFactory factory = new DbFactory();
        
        System.out.println( "Insert... " );
        factory.insert();
        
        System.out.println("min " + factory.getMinIns());
        System.out.println("max " + factory.getMaxIns());
        System.out.println("avg " + factory.getAvgIns());
        
        
        System.out.println( "Select... " );
        
        factory.select();
        
        System.out.println("min " + factory.getMinSel());
        System.out.println("max " + factory.getMaxSel());
        System.out.println("avg " + factory.getAvgSel());
        
        factory.closeConnection();
        
    }
}
