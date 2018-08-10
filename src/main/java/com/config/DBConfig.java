package com.config;
/**
 * 数据库连接配置
 * @Description:   
 * @author: hang   
 * @date: 2018年8月9日下午2:40:45 
 * @mail 977767937@qq.com   
 * @version V1.7
 */
public class DBConfig {
	
	private String driver;//数据库驱动
	private String url;//数据库链接
	private String user;//数据库用户名
	private String password;//数据库密码
	
	
	public DBConfig() {}
	public DBConfig(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
