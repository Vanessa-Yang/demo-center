package org.example.design.facade;

import java.sql.Connection;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 15:25
 **/
public class HelperFacade {
    public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName) {
        Connection con = null;
        switch (dbType) {
            case MYSQL -> {
                con = MySqlHelper.getMySqlDBConnection();
                MySqlHelper mySqlHelper = new MySqlHelper();
                switch (reportType) {
                    case HTML -> mySqlHelper.generateMySqlHTMLReport(tableName, con);
                    case PDF -> mySqlHelper.generateMySqlPDFReport(tableName, con);
                }
            }
            case ORACLE -> {
                con = OracleHelper.getOracleDBConnection();
                OracleHelper oracleHelper = new OracleHelper();
                switch (reportType) {
                    case HTML -> oracleHelper.generateOracleHTMLReport(tableName, con);
                    case PDF -> oracleHelper.generateOraclePDFReport(tableName, con);
                }
            }
        }
    }

    public enum DBTypes {
        MYSQL, ORACLE;
    }

    public enum ReportTypes {
        HTML, PDF;
    }
}
