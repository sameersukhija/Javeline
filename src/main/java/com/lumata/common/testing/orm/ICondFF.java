package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public interface ICondFF extends IBuild {

	Statement getStatement();
	void setStatement( Statement statement );
	ICondFF append( MysqlStatement type, IExprFF... expr_list );
	ICondFF append( MysqlStatement type, ICondFF... cond );
	
}
