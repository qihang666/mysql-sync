package com.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
/**
 * DB 工具类
 * @Description:   
 * @author: hang   
 * @date: 2018年8月10日上午10:58:57   
 * @mail 977767937@qq.com   
 * @version V1.8
 */
public class DbUtil {
	protected static Logger log = LoggerFactory.getLogger(DbUtil.class);
	
	 private static final String SQL = "SELECT * FROM ";// 数据库操作
	/**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames(Connection conn) {
        List<String> tableNames = new ArrayList<>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = (DatabaseMetaData) conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName,Connection conn) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = (PreparedStatement) conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = (ResultSetMetaData) pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                } catch (SQLException e) {
                    log.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName,Connection conn ) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = (PreparedStatement) conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = (ResultSetMetaData) pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                } catch (SQLException e) {
                    log.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

   

}
