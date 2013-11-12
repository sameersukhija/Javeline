package com.lumata.common.testing.orm;

import java.util.List;

public interface IWhere extends IQueryTemplate, IBuild {

	IOrder order( List<String> order );
	
}
