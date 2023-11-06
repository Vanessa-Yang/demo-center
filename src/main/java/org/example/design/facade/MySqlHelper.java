package org.example.design.facade;

import java.sql.Connection;

/**
 * @Description Facade Design Pattern - Set of Interfaces
 * | Provide a unified interface to a set of interfaces in a subsystem. Facade Pattern
 * | defines a higher-level interface that makes the subsystem easier to use.
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 15:11
 **/
public class MySqlHelper {
    public static Connection getMySqlDBConnection(){
        // get MySql DB connection using connection parameters
        return null;
    }

    public  void generateMySqlPDFReport(String tableName, Connection con){
        // get data from table amd generate pdf report
        System.out.println("mysql -> pdf");
    }

    public void generateMySqlHTMLReport(String tableName, Connection con){
        // get data from table and generate html report
        System.out.println("mysql -> html");
    }
}
