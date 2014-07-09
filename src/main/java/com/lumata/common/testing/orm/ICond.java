package com.lumata.common.testing.orm;

import java.util.Map;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public interface ICond extends IBuild {

	Statement getStatement();
	void setStatement( Statement statement );
	ICond append( MysqlStatement type, IExprFV... expr_list );
	ICond append( MysqlStatement type, IExprFF... expr_list );
	ICond append( MysqlStatement type, ICond... cond );
	Map<Enum<?>, String> getPlaceHolders();
	String build();
	
}
