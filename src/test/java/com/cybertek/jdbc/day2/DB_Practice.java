package com.cybertek.jdbc.day2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DB_Practice {

    public static void main(String[] args) throws SQLException {
        // print out all data from Jobs Table
        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM JOBS");

//        // ITERATE OVER THE RESULTSET
//        rs.next();
//        // get first 2 column
//        System.out.println( rs.getString(1) );

        while( rs.next() ) {
            System.out.println( rs.getString(1) );
        }

        System.out.println("columnCount = " + DB_Utility.getColumnCNT() );

    }


}
