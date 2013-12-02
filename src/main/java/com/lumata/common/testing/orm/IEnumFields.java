package com.lumata.common.testing.orm;

import com.lumata.common.testing.annotations.mysql.Column;

public interface IEnumFields {

	String name();
	String simpleName();
	String table();
	Column col();
	
}
