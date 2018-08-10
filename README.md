###基于MySql "load data infile" 的数据同步工具，适应于快速同步数据，类似游戏合服同步， 
Data synchronization tool based on MySql "load data infile", suitable for fast data synchronization, similar to game synchronization.

------------


一.使用例子
######  - 1.在mysql执行resources\example 下的init.sql 生成3个数据库分别为t1 t2 t3 本例子演示 t1 t2 的数据全部导入到 t3 中


######  - 2.在resources/db.properties 有如下配置from1 from1 为数据源 target为目标数据库


    jdbc.url.from1=jdbc:mysql://127.0.0.1:3306/t1?useUnicode=true&characterEncoding=UTF8&autoReconnect=true jdbc.driver.from1=com.mysql.jdbc.Driver
    jdbc.user.from1=root
    jdbc.password.from1=root
    
    jdbc.url.from2=jdbc:mysql://127.0.0.1:3306/t2?useUnicode=true&characterEncoding=UTF8&autoReconnect=true jdbc.driver.from2=com.mysql.jdbc.Driver
    jdbc.user.from2=root
    jdbc.password.from2=root
    
    jdbc.url.target=jdbc:mysql://127.0.0.1:3306/t3?useUnicode=true&characterEncoding=UTF8&autoReconnect=true jdbc.driver.target=com.mysql.jdbc.Driver jdbc.user.target=root jdbc.password.target=root
    
    exclude-table=data2|data3
    exclude-table-column=data1->id-age|data2->id,
    

exclude-table：表示需要排除的表 如果有多张则用|分割 例如配置 table1|table2 则同步过程中排除 table1 和 table2 的表 exclude-table-column:表示需要排除某张表中的某个字段不需要同步数据 例如配置 data1->id-age 则表示 排除data1表中 的id字段和age字段的数据不需要同步如有多个表字段用|分开

###### - 3 运行com.example.Example类中的main方法实现快速同步
    public class Example {
    
    	public static void main(String[] args) {
		    //创建同步配置 加载db.properties配置信息
    		SyncConfig syncConfig = new SyncConfig();
			//源数据源1构建 sql 脚本与数据文件
    		BuildDataScript.buildSqlScriipt(SourceType.FROM1,syncConfig);
			//源数据源2构建 sql 脚本与数据文件
    		BuildDataScript.buildSqlScriipt(SourceType.FROM2,syncConfig);
			//需要同步的目标数据源加载 sql脚本与文件 导入数据
    		BuildDataScript.loadSqlScriipt(SourceType.TARGET,syncConfig);
			//关闭Connection
    		syncConfig.closeAllConnection();
    	}
    
    }
    