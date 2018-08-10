package com.script;

import java.io.File;
import java.sql.Connection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.config.SyncConfig;
import com.config.SourceType;
import com.util.DbUtil;
/**
 * 构建mysql导出 导入语句
 * @Description:   
 * @author: hang   
 * @date: 2018年8月10日上午10:58:57   
 * @mail 977767937@qq.com   
 * @version V1.8
 */
public class BuildDataScript {
	
	public static String PATH = BuildDataScript.class.getClassLoader().getResource("").getPath();
	
	public static void buildSqlScriipt(SourceType type,SyncConfig syncConfig) {
		 try {
			   Connection conn = syncConfig.getConnMap().get(type);
	           List<String> tables = DbUtil.getTableNames(conn);
	           StringBuffer outAllSql = new StringBuffer();
	           StringBuffer loadAllSql = new StringBuffer();
	           for(String table:tables) {
	        	   if(syncConfig.getExcludeTables().contains(table)) {
	        		   continue;
	        	   }
	        	   File dataFile = new File(PATH+"data/"+table+"_"+type.getType()+".txt");
	        	   if(dataFile.exists()) {
	        		   dataFile.delete();
	        	   }
	        	   if(!new File(PATH+"data/").exists()) {
	        		   new File(PATH+"data/").mkdirs();
	        	   }
	        	   StringBuffer outSql = new StringBuffer();
	        	   List<String> columns = DbUtil.getColumnNames(table, conn);
	        	   String columnString = besetColumns(columns,syncConfig.getExcludeTableColumns().get(table));
	        	   outSql.append("select ")
	        	   .append(columnString)
	        	   .append(" from ")
	        	   .append(table)
	        	   .append(" into outfile ")
	        	   
	        	   .append(" \""+dataFile.getPath()+"\" ")
	        	   .append(" fields terminated by ',';");
	        	   String outSting = outSql.toString().replaceAll("\\\\", "/");
	        	   outAllSql.append(outSting).append("\n");
	        	   
	        	   StringBuffer loadSql = new StringBuffer();
	        	   loadSql.append("load data infile \"")
	        	   .append(dataFile.getPath()+"\"")
	        	   .append(" into table ")
	        	   .append(table)
	        	   .append(" character set utf8 fields terminated by ',' ")
	        	   .append("(")
	        	   .append(columnString)
	        	   .append(")")
	        	   .append(";");
	        	   
	        	   String load = loadSql.toString().replaceAll("\\\\", "/");
	        	   loadAllSql.append(load).append("\n");
	        	   
	           }
	            File file = new File(PATH+"sql/"+type.getType()+".sql");
			    if(file.exists()) {
					file.delete();
			    }
				FileUtils.writeStringToFile(file,outAllSql.toString(),"utf-8");
				RunSqlScript.runScript(conn, file);
				conn.close();
				
				File file1 = new File(PATH+"sql/load"+type.getType()+".sql");
				if(file1.exists()) {
				   file1.delete();
				}
			    FileUtils.writeStringToFile(file1,loadAllSql.toString(),"utf-8");
			    syncConfig.getLoadFiles().add(file1);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 
	}
	
	
	public static void loadSqlScriipt(SourceType type,SyncConfig syncConfig) {
		  Connection conn = syncConfig.getConnMap().get(type);
		  if(!syncConfig.getLoadFiles().isEmpty()) {
			  for(File file:syncConfig.getLoadFiles()) {
				  RunSqlScript.runScript(conn, file);
			  }
		  }
		
	}
	
	public static String besetColumns(List<String> columns, HashSet<String> exColumns) {
		StringBuffer bu = new StringBuffer();
		for(String c:columns) {
			if(exColumns!= null ) {
				if(exColumns.contains(c)) {
					continue;
				}
				
			}
			bu.append("`").append(c).append("`").append(",");
		}
		return bu.toString().substring(0, bu.toString().length()-1);
		
	}
	
}
