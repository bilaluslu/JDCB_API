package com.cybertek.jdbc.day1;

import java.sql.*;

public class DB_Connection {

    public static void main(String[] args) throws SQLException {

        // CONNECTION --> STATEMENT --> RESULT SET

        // Driver manager is used to get the connection
        // The IP address is the IP address of EC2 instance that have Oracle database

        String connectionStr = "jdbc:oracle:thin:@34.224.4.31:1521:XE";
        String username = "hr";
        String password = "hr";

        //JDBC ship with JDK , and has a lot of pre-written codes to work with dataase
        // everything we do below comes from java.sql package

        // creating connection object using DriverManager's static method Connection
        Connection conn = DriverManager.getConnection( connectionStr, username, password );

        // creating statement object using  the connection we have established
        Statement statement = conn.createStatement();

        // ResultSet object is what we use to store the actual result we get from query
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

        // ResultSet comes with a cursor used to navigate between rows
        // initially the cursor is at before first location (right before the first row)
        //in order to come to first row we need to move cursor
        // next() method is used to move the cursor and return the result as boolean

        resultSet.next();   // currently we are at the very first row

        // getting the column data we use multiple get methods available in ResultSet
        // print out region id and region name, both as String

        System.out.println( "First column value using index --> " + resultSet.getString(1) );
        System.out.println( "First column value using column name --> " + resultSet.getString("REGION_ID") );

        System.out.println( "Second column value using index --> " + resultSet.getString(2) );
        System.out.println( "Second column value using column name --> " + resultSet.getString("REGION_NAME") );


        System.out.println("THE END");



    }
}
