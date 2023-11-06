package org.example.design.facade;

import java.sql.Connection;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 15:16
 **/
public class OracleHelper {
    public static Connection getOracleDBConnection(){
        // get Oracle DB connection using connection parameters
        return null;
    }

    public void generateOraclePDFReport(String tableName, Connection con){
        // get data from table and generate pdf report
        System.out.println("oracle -> pdf");
    }

    public void generateOracleHTMLReport(String tableName, Connection con){
        // get data from table and generate pdf report
        System.out.println("oracle -> html");
    }
}
