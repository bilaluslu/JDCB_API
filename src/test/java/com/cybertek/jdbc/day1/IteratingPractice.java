package com.cybertek.jdbc.day1;

import java.sql.*;

public class IteratingPractice {

    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@34.224.4.31:1521:XE";
        String username = "hr" ;
        String password = "hr" ;
        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        Statement stmnt = conn.createStatement();
        ResultSet rs   =   stmnt.executeQuery("SELECT * FROM COUNTRIES") ;

        // as long as rs.next() return true I know I have next row to print the data
        // we will keep looping as long as rs.next() return true

        while ( rs.next() == true ){

            System.out.println( rs.getString(1) + " " + rs.getString(2)
                                + " " + rs.getString(3) );


        }





    }
}
