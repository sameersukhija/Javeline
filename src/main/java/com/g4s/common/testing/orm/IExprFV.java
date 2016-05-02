package com.g4s.common.testing.orm;

public interface IExprFV {

	Enum<?> getField();
	Op.Types getOp();
	Object getValue();
	Object[] getValues();
	boolean getUsePlaceHolder();
	void setField( Enum<?> field );
	void setOp( Op.Types op );
	void setValue( Object value );
	void setValues( Object... value );
	void setUsePlaceHolder( boolean use_place_holder );
	
}
