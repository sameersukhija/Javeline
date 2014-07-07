package com.lumata.e4o.dao.tenant;

import com.lumata.common.testing.database.Mysql;

public class DAO {
	
	private Mysql mysql;
	
	public DAO( Mysql mysql ) {
		
		this.mysql = mysql;
	
	}
		
	protected Mysql getMysql() {
		return mysql;
	}
	
	protected void setMysql( Mysql mysql ) {
		this.mysql = mysql;
	}

		
}
