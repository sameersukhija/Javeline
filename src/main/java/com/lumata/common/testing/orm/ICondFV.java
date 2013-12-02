package com.lumata.common.testing.orm;

import java.util.Map;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public interface ICondFV {

	ICondFV append( MysqlStatement type, IExprFV... expr_list );
	ICondFV append( MysqlStatement type, ICondFV... cond );
	Map<Enum<?>, String> getPlaceHolders();
	String build();
	
}
