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
import java.util.HashMap;
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

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        DBStrategy db = new MySqlDBStrategy();
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        List<Map<String, Object>> rawData = db.retreiveAllRecordsForTable("author", 0);
//        int deletedRecords = db.deleteRecordbyPrimaryKey("author", "author_id", 4);
//        List<Map<String, Object>> rawData2 = db.retreiveAllRecordsForTable("author", 0);
//
//        db.closeConnection();
//        System.out.println(deletedRecords);
//        System.out.println(rawData);
//        System.out.println(rawData2);
//    }

}
