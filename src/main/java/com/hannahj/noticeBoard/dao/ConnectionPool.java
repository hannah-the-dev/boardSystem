
package com.hannahj.noticeBoard.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public enum ConnectionPool {
	INSTANCE;
//    private Connection connection;
    private static BasicDataSource DS = new BasicDataSource();
    
    static {
    	DS.setUrl("jdbc:mysql://192.168.23.17:3306/kopoctc");
    	DS.setDriverClassName("com.mysql.cj.jdbc.Driver");
        DS.setUsername("root");
        DS.setPassword("kopoctc");
        DS.setMinIdle(5);
        DS.setMaxIdle(10);
        DS.setMaxOpenPreparedStatements(100);
    }
    
    private ConnectionPool() {
    }
	
	public static ConnectionPool getInstance(){
		return INSTANCE;
	}

    public static Connection getConnection() throws SQLException {
		return DS.getConnection();
    }
}
