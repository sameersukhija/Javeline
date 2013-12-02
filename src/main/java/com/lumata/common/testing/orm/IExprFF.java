package com.lumata.common.testing.orm;

public interface IExprFF {

	Enum<?> getLeftField();
	Op.Types getOp();
	Enum<?> getRightField();
	void setLeftField( Enum<?> field );
	void setOp( Op.Types op );
	void setRightField( Enum<?> field );
	
}
