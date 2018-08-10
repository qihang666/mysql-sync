package com.script;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行mysql脚本
 * @Description:   
 * @author: hang   
 * @date: 2018年8月10日下午2:15:24   
 * @version V1.7
 */
public class RunSqlScript {
	protected static Logger log = LoggerFactory.getLogger(RunSqlScript.class);	
	
	public static void runScript(Connection conn,File file) {
		   long start = System.currentTimeMillis();
		try {
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setLogWriter(null);//设置是否输出日志
			runner.runScript(new FileReader(file));
			long time =System.currentTimeMillis() - start;
			log.info("RunSqlScript user time="+time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
