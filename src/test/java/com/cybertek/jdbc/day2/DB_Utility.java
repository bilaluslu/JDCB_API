package com.cybertek.jdbc.day2;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Utility {

    // adding static field so we can access in all static methods
    private static Connection conn ;
    private static ResultSet rs;

    /*
       * we want to store certain row data as a map
       * give me number 3 row --> Map<String,String>  ( region_id :3 , region_name : Asia )
     */






    /*
     * @param columnIndex the column you want to get a list out of
     * @return  List of String that contains entire column data from 1st row to last row
     */
    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataList = new ArrayList<>();
        try {
            rs.beforeFirst();   // moving back the cursor to before first location just in case

            while ( rs.next() ) {

                String data = rs.getString(columnName);
                // getting the data from that column and adding to the list
                columnDataList.add( data );
            }
        } catch (SQLException throwables) {
            System.out.println("ERROR WHILE getColumnDataAsList ");
            throwables.printStackTrace();
        }


        return columnDataList;
    }


    /*
     * @param columnIndex the column you want to get a list out of
     * @return  List of String that contains entire column data from 1st row to last row
     */
    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> columnDataList = new ArrayList<>();
        try {
            rs.beforeFirst();   // moving back the cursor to before first location just in case

        while ( rs.next() ) {

            // getting the data from that column and adding to the list
            columnDataList.add(rs.getString(columnIndex));
        }
            } catch (SQLException throwables) {
            System.out.println("ERROR WHILE getColumnDataAsList ");
                throwables.printStackTrace();
            }


        return columnDataList;
    }





    /*
     * Getting single column cell value at certain row
     * row 2 column 3  -->> the data
     * */

    /**
     * Getting single column cell value at certain row
     * @param rowNum    row number we want to get data from
     * @param columnIndex  column index we want to get the data from
     * @return the data in String
     */
    public static String getColumnDataAtRow (int rowNum , int columnIndex){

        /*
        take home tasks
        improve this method and check for valid rowNum and ColumnIndex
        if invalid return emptyString
         */

        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnIndex ) ;

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }

        return result ;
    }

    /*
     * @param rowNum    row number we want to get data from
     * @param columnName  column index we want to get the data from
     * @return the data at that row with that column name
     */
    public static String getColumnDataAtRow (int rowNum , String columnName){   // overloaded method

        /*
        take home tasks
        improve this method and check for valid rowNum and ColumnIndex
        if invalid return emptyString
         */

        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnName ) ;

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }

        return result ;
    }

    /*
     * @param rowNum the row number you want the list from
     * @return List of String that contains the row data
     */

    //  Getting the entire  row as List<String>
    public static List<String> getRowDataAsList(int rowNum){

        List<String> rowDataList = new ArrayList<>();

        // how to move to that Row with rowNum
        try {
            rs.absolute( rowNum );

            // iterate over each and every column and add the value to the list
            for (int i = 1 ; i <= getColumnCNT() ; i++) {
                rowDataList.add( rs.getString(i) );
            }
            rs.beforeFirst();   // moving the cursor back to before first location just in case
        } catch (SQLException throwables) {
            System.out.println( "ERROR WHILE getRowDataAsList" );
            throwables.printStackTrace();
        }

        return rowDataList;
    }


    public static int getRowCount(){
        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow();
            // moving back the cursor to before first location just in case
            rs.beforeFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowCount;
    }

    /*
     * a method to display all the data in the result set
     *
     * */
    public static void displayAllData(){

        // get the first row data  | without knowing the column names
        int colCount = DB_Utility.getColumnCNT() ;
        // in order to get whole result cursor must be at before first location !

        try {
            // in order to start from beginning, we should be at beforefirst location
            rs.beforeFirst();   // this is for below loop to work
            while (rs.next() == true) { // row iteration

                for (int i = 1; i <= colCount; i++) { // column iteration
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println(); /// adding a blank line for next line
            }
            // now the cursor is at after last location
            // move it back to before first location so we can have no issue calling the method again
            rs.beforeFirst();   // this is for next method that might need to be at before first location


        }catch(SQLException e){
            System.out.println("ERROR WHILE GETTING ALL DATA");
            e.printStackTrace();
        }

    }



    /*
        * a method to get the column of the current ResutSet
        *
        *  getColumnCNT()
     */

    public static int getColumnCNT(){

        int columnCount = 0;
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException throwables) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS");
            throwables.printStackTrace();
        }


        return columnCount;
    }

    /*
     * a static method to create connection
     * with valid url and username password
     * */
    public static void createConnection() {

        String connectionStr = "jdbc:oracle:thin:@52.71.242.164:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            conn = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException throwables) {
            System.out.println("CONNECTION HAS FAILED!");
            throwables.printStackTrace();
        }

    }
    /*
     * a static method to get the ResultSet object
     * with valid connection by executing query
     * */
    public static ResultSet runQuery(String query){

        try {
            Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;

    }

}
