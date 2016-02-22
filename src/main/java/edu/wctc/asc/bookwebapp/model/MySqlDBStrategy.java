/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Adam
 */
public class MySqlDBStrategy implements DBStrategy {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);

    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * Make sure you close and open connection when using this method at return
     * type as an array
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> retreiveAllRecordsForTable(String tableName, int maxRecords)
            throws SQLException {
        String sql;
        if (maxRecords < 1) {
            sql = "select * from " + tableName;
        } else {
            sql = "select * from " + tableName + " limit " + maxRecords;
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<Map<String, Object>> records = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int colNo = 1; colNo <= columnCount; colNo++) {
                Object colData = rs.getObject(colNo);
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }

    @Override
    public int deleteRecordByPrimaryKey(String tableName, String primarykeyName, Object primaryKeyValue) throws SQLException {
        int recordsDeleted = 0;
        PreparedStatement pstmt = null;

        final String deleteString = "Delete FROM " + tableName + " WHERE " + primarykeyName + " =?";

        //final String sql = "Delete FROM " + tableName + " WHERE " + primarykeyName +  primaryKeyVal;
        //pstmt = conn.prepareStatement(sql);
        pstmt = conn.prepareStatement(deleteString);
        pstmt.setObject(1, primaryKeyValue);
        recordsDeleted = pstmt.executeUpdate();

        return recordsDeleted;
    }

    /*
	 * Builds a java.sql.PreparedStatement for an sql insert
	 * @param conn - a valid connection
	 * @param tableName - a <code>String</code> representing the table name
	 * @param colDescriptors - a <code>List</code> containing the column descriptors for
	 * the fields that can be inserted.
	 * @return java.sql.PreparedStatement
	 * @throws SQLException
     */
    @Override
    public int updateRecords(String tableName, List<String> colNames, List<Object> colValues,
            String pkColName, Object value)
            throws SQLException, Exception {
        PreparedStatement pstmt = null;
        int recordsUpdated = 0;

        
        try {
            pstmt = buildUpdateStatement(conn, tableName, colNames, pkColName);

            final Iterator i = colValues.iterator();
            int index = 1;
            Object obj = null;

            
            while (i.hasNext()) {
                obj = i.next();
                pstmt.setObject(index++, obj);
            }
            
            pstmt.setObject(index, value);

            recordsUpdated = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw e;
            } 
        } 

        return recordsUpdated;
    }

    @Override
    public int insertRecord(String tableName, List<String> colNames, List<Object> colValues) throws SQLException {
        int recordsInserted = 0;
        PreparedStatement prepStmt = null;

        prepStmt = buildInsertStatement(conn, tableName, colNames);

        final Iterator i = colValues.iterator();
        int index = 1;

        while (i.hasNext()) {
            final Object obj = i.next();
            prepStmt.setObject(index++, obj);
        }

        recordsInserted = prepStmt.executeUpdate();

        prepStmt.close();
        conn.close();

        return recordsInserted;
    }

    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName,
            List colDescriptors, String whereField)
            throws SQLException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL = sql.toString();
        return conn_loc.prepareStatement(finalSQL);
    }

    private PreparedStatement buildInsertStatement(Connection conn, String tableName, List colNames) throws SQLException { //no values needed because they are provided from the list

        StringBuffer sql = new StringBuffer("Insert Into " + tableName + " (");
        final Iterator i = colNames.iterator();
        while (i.hasNext()) {
            sql.append(i.next() + ", ");
        }

        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ") Values (");
        for (int m = 0; m < colNames.size(); m++) {
            sql.append("?, ");
        }
        final String finalSQL = ((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")) + ")");
        return conn.prepareStatement(finalSQL);

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Map<String, Object>> rawData = db.retreiveAllRecordsForTable("author", 0);
        db.closeConnection();
        //int deletedRecords = db.deleteRecordByPrimaryKey("author", "author_id", 4);
        List<String> colNames = Arrays.asList("author_name");
        List<Object> colValues = Arrays.asList("Time");

        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        int updatedRecords = db.updateRecords("author", colNames, colValues, "author_id", 4);
        //List<Map<String, Object>> rawData2 = db.retreiveAllRecordsForTable("author", 0);

        db.closeConnection();
        //System.out.println(deletedRecords);
        System.out.println(updatedRecords);
        System.out.println(rawData);
        //System.out.println(rawData2);
    }

}
