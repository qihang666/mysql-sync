package com.example;

import com.config.SyncConfig;
import com.config.SourceType;
import com.script.BuildDataScript;

public class Example {
	

	public static void main(String[] args) {
		SyncConfig syncConfig = new SyncConfig();//创建同步配置 加载db.properties配置信息
		BuildDataScript.buildSqlScriipt(SourceType.FROM1,syncConfig);//源数据源1构建 sql 脚本与数据文件
		BuildDataScript.buildSqlScriipt(SourceType.FROM2,syncConfig);//源数据源2构建 sql 脚本与数据文件
		BuildDataScript.loadSqlScriipt(SourceType.TARGET,syncConfig);//需要同步的目标数据源加载 sql脚本与文件 导入数据
		syncConfig.closeAllConnection();//关闭Connection
	}

}
