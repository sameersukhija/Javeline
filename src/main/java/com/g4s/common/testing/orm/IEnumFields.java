package com.g4s.common.testing.orm;

import com.g4s.common.testing.annotations.mysql.Column;

public interface IEnumFields {

	String name();
	String simpleName();
	String table();
	Column col();
	String function();
	Boolean isFunction();
	
}
