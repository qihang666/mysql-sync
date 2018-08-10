package com.config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
/**
 * 数据源类型
 * @Description:   
 * @author: hang   
 * @date: 2018年8月10日上午10:58:57   
 * @mail 977767937@qq.com   
 * @version V1.8
 */
public class SyncConfig {

	private Map<SourceType, Connection> connMap = new HashMap<SourceType, Connection>();
	
	// 排除的表
	private Set<String> excludeTables = new HashSet<String>();
	// 排除的字段
	private  Map<String, HashSet<String>> excludeTableColumns = new HashMap<String, HashSet<String>>();
	//加载的数据脚本
	private List<File> loadFiles = new ArrayList<File>();
	
	

	public SyncConfig() {
		init();
	}
	public void init() {
		try {
			Configuration configuration = new PropertiesConfiguration(
					SyncConfig.class.getClassLoader().getResource("").getPath() + "db.properties");
			String eTable = configuration.getString("exclude-table");
			String eColumn = configuration.getString("exclude-table-column");
			if(StringUtils.isNotBlank(eTable)) {
				excludeTables.addAll(Arrays.asList(eTable.split("\\|")));
			}
			
			if(StringUtils.isNotBlank(eColumn)) {
				String es[] = eColumn.split("\\|");
				if(es!= null&& es.length>0) {
					for(String e: es) {
						if(StringUtils.isNotBlank(e)) {
							String cs[] = e.split("->");
							if(cs!= null&& cs.length==2) {
								String key = String.valueOf(cs[0]);
								if(!excludeTableColumns.containsKey(key)) {
									excludeTableColumns.put(key, new HashSet<String>());
								}
								HashSet<String> columns = excludeTableColumns.get(key);
							    columns.addAll(Arrays.asList(cs[1].split("-")));
							}
						}
					}
				}
			}
			
			buildConnection(SourceType.FROM1,configuration);
			buildConnection(SourceType.FROM2,configuration);
			buildConnection(SourceType.TARGET,configuration);
			
			System.err.println(excludeTables.size());
			System.err.println(excludeTableColumns.size());
			
			
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	public void buildConnection(SourceType type,Configuration configuration) {
		try {
		  String url = configuration.getString("jdbc.url."+type.getType());
          String driver = configuration.getString("jdbc.driver."+type.getType());
          String username = configuration.getString("jdbc.user."+type.getType());
          String password = configuration.getString("jdbc.password."+type.getType());
           Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url, username, password);
			connMap.put(type, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeAllConnection() {
		try {
			for(Connection conn: connMap.values()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  Map<SourceType, Connection> getConnMap() {
		return connMap;
	}
	public  Set<String> getExcludeTables() {
		return excludeTables;
	}
	public  Map<String, HashSet<String>> getExcludeTableColumns() {
		return excludeTableColumns;
	}
	public List<File> getLoadFiles() {
		return loadFiles;
	}
	
	

}
