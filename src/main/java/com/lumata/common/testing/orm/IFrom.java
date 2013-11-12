package com.lumata.common.testing.orm;

import java.util.List;

public interface IFrom extends IQueryTemplate, IBuild {
	
	IWhere where( List<String> filter );
	
}
