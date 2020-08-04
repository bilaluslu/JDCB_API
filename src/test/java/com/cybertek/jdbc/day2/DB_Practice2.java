package com.cybertek.jdbc.day2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Practice2 {

    public static void main(String[] args) throws SQLException {

        // print out all data from Jobs Table
        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM REGIONS");

        // this method call is displaying the data of the resultset
        DB_Utility.displayAllData();


        //move resultset cursor to second row
        rs.absolute(2);

        DB_Utility.displayAllData();    // if we call again it will start over

        System.out.println( "Given Row number and Column Index --> " + DB_Utility.getColumnDataAtRow(3, 2));
        System.out.println( "Given Row number and Column Name  --> " + DB_Utility.getColumnDataAtRow(3, "REGION_NAME"));


/*
        // get the first row data  | without knowing the column names
        int colCount = DB_Utility.getColumnCNT() ;
        // in order to get whole result cursor must be at before first location !
        while(rs.next() == true){ // row iteration

            for (int i = 1; i <= colCount ; i++) { // column iteration
                System.out.print(  rs.getString( i )  + "\t" );
            }
            System.out.println(); /// adding a blank line for next line

        }
*/









    }

}
