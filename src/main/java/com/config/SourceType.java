package com.config;
/**
 * 数据源类型
 * @Description:   
 * @author: hang   
 * @date: 2018年8月10日上午10:58:57   
 * @mail 977767937@qq.com   
 * @version V1.8
 */
public enum SourceType {

	FROM1("from1"),
	FROM2("from2"),
	TARGET("target"),

	;
	
	private String type;

	private SourceType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;

	}

	public void setType(String type) {
		this.type = type;
	}

}
