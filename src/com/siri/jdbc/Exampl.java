package com.siri.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exampl {
    
    public static Connection mySqlConnection = null;

    public static void main(String[] args) {
        try {
            mySqlConnection = connectToDB();
            //System.out.println(mySqlConnection.toString());
            

            Statement statement = mySqlConnection.createStatement();
            ResultSet result = statement.executeQuery("select * from author");
            while(result.next()) {
                System.out.println(result.getString(1) + " , " + result.getString(2));
            }
            
            //getLoanDetails("C4444");
            //getLoanDetailsByPrepared("C4444");
            //updateLoanDetails();
            incokeProc(15);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    public static void getLoanDetails(String custno) throws SQLException {
        Statement statement = mySqlConnection.createStatement();
        String query = "select * from loan " + " where custno = '" + custno + "'"; 
        
        //select * from loan  where custno = 'c4444' 
        ResultSet result = statement.executeQuery(query);
        while(result.next()) {
            System.out.println(result.getString(1) + " , " + result.getString(2));
        }
        
        
    }
    
    public static void getLoanDetailsByPrepared(String custno) throws SQLException {
        PreparedStatement statement = mySqlConnection.prepareStatement("select * from loan  where loandays = ?");
        statement.setInt(1, 10);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            System.out.println(result.getString(1) + " , " + result.getString(2));
        }
        
        
    }
    public static void updateLoanDetails() throws SQLException {
        Statement statement = mySqlConnection.createStatement();
        //String query = "select * from loan " + " where custno = '" + custno + "'"; 
        //update loan set loandays = loandays + count where custno = 'C4444'
        //select * from loan  where custno = 'c4444' 
        Integer counter = statement.executeUpdate("update loan c set c.loandays = c.loandays + 1 where custno = 'C4444'");
        System.out.println(counter);
        
        
    }
    
    public static void incokeProc(int loandaysMax) throws SQLException {
        CallableStatement statement = mySqlConnection.prepareCall("Call fine_proc(?)");
        statement.setInt(1, loandaysMax);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            System.out.println(result.getString(1) + " , " + result.getString(2));
        }
        
        
    }
    
    
    public static Connection connectToDB() throws Exception {        
        Class.forName("com.mysql.jdbc.Driver");        
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/angularclass","root","root");    
        
        return connection;
        
    }

}

